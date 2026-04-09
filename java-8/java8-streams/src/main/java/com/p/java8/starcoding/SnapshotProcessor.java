package com.p.java8.starcoding;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class SnapshotProcessor {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String OUTPUT_ROOT = "D:\\v-dir";
    private static final Path BASE_JSON = Paths.get(OUTPUT_ROOT, "base.json");

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        writeBaseProcessedJsonFile();

        processSnapshots();
        buildQuestionAnswerFiles();
        buildDatewiseMessageFiles();

        System.out.println("Done in " + (System.currentTimeMillis() - start) + " ms");
    }

    // ---------------- FILE UTILS ----------------

    static <T> T readJson(Path path, TypeReference<T> type) throws IOException {
        return MAPPER.readValue(path.toFile(), type);
    }

    static void writeJson(Path path, Object data) throws IOException {
        Files.createDirectories(path.getParent());
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), data);
    }

    // ---------------- CORE ----------------

    static void writeBaseProcessedJsonFile() throws IOException {
        List<Map<String, Object>> dummy = List.of(); // replace with JsonFileMapWithDetails
        writeJson(BASE_JSON, dummy);
    }

    static void processSnapshots() throws Exception {
        List<Map<String, Object>> baseRows =
                readJson(BASE_JSON, new TypeReference<>() {});

        for (Map<String, Object> row : baseRows) {

            String slug = (String) row.get("slug");
            Path outputDir = getOutputDir(slug);

            List<Map<String, Object>> conversations = loadConversationRecords(
                    Paths.get((String) row.get("location"))
            );

            if (conversations == null) continue;

            List<Map<String, Object>> messages = new ArrayList<>();

            for (Map<String, Object> conv : conversations) {
                List<Map<String, Object>> msgs =
                        (List<Map<String, Object>>) conv.get("messages");

                messages.addAll(msgs);
            }

            writeJson(outputDir.resolve("message.json"), messages);
        }
    }

    // ---------------- LOAD ----------------

    static List<Map<String, Object>> loadConversationRecords(Path path) throws IOException {

        if (!Files.exists(path)) return null;

        if (Files.isRegularFile(path)) {
            return readJson(path, new TypeReference<>() {});
        }

        if (Files.isDirectory(path)) {
            return mergeConversationShardFiles(path);
        }

        return null;
    }

    static List<Map<String, Object>> mergeConversationShardFiles(Path dir) throws IOException {
        List<Map<String, Object>> merged = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "conversations-*.json")) {
            for (Path file : stream) {
                List<Map<String, Object>> data =
                        readJson(file, new TypeReference<>() {});
                merged.addAll(data);
            }
        }

        writeJson(dir.resolve("merged-conversations.json"), merged);
        return merged;
    }

    // ---------------- QnA ----------------

    static void buildQuestionAnswerFiles() throws Exception {

        List<Map<String, Object>> baseRows =
                readJson(BASE_JSON, new TypeReference<>() {});

        for (Map<String, Object> row : baseRows) {

            String slug = (String) row.get("slug");
            Path dir = getOutputDir(slug);

            List<Map<String, Object>> messages =
                    readJson(dir.resolve("message.json"), new TypeReference<>() {});

            Map<String, List<String>> qna = buildQnA(messages);

            writeJson(dir.resolve("qNa.json"), qna);
        }
    }

    static Map<String, List<String>> buildQnA(List<Map<String, Object>> messages) {

        Map<String, List<String>> map = new LinkedHashMap<>();
        String activeUserId = null;

        for (Map<String, Object> msg : messages) {

            Boolean isUser = (Boolean) msg.get("isUserMessage");
            String id = (String) msg.get("id");

            if (Boolean.TRUE.equals(isUser)) {
                activeUserId = id;
                map.put(id, new ArrayList<>());
            } else if (activeUserId != null) {
                map.get(activeUserId).add(id);
            }
        }

        return map;
    }

    // ---------------- DATEWISE ----------------

    static void buildDatewiseMessageFiles() throws Exception {

        List<Map<String, Object>> baseRows =
                readJson(BASE_JSON, new TypeReference<>() {});

        for (Map<String, Object> row : baseRows) {

            String slug = (String) row.get("slug");
            Path dir = getOutputDir(slug);

            List<Map<String, Object>> messages =
                    readJson(dir.resolve("message.json"), new TypeReference<>() {});

            Map<String, List<String>> result = messages.stream()
                    .filter(m -> Boolean.TRUE.equals(m.get("isUserMessage")))
                    .collect(Collectors.groupingBy(
                            m -> formatDate((String) m.get("createdOn")),
                            LinkedHashMap::new,
                            Collectors.mapping(m -> (String) m.get("id"), Collectors.toList())
                    ));

            writeJson(dir.resolve("datewiseMessages.json"), result);
        }
    }

    // ---------------- HELPERS ----------------

    static Path getOutputDir(String slug) {
        return Paths.get(OUTPUT_ROOT, "itr2", slug);
    }

    static String formatDate(String dateStr) {
        try {
            return Instant.parse(dateStr)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .toString();
        } catch (Exception e) {
            return LocalDate.now().toString(); // fallback
        }
    }
}