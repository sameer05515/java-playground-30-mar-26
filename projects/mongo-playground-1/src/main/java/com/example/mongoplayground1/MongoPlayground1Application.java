package com.example.mongoplayground1;

import com.mongodb.ConnectionString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.mongoplayground1.entity.Book;
import com.example.mongoplayground1.repository.BookRepository;
import com.example.mongoplayground1.repository.MessageRepository;

@SpringBootApplication
public class MongoPlayground1Application implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(MongoPlayground1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(MongoPlayground1Application.class, args);
    }

    private BookRepository bookRepository;
    private final Environment environment;
    private final MongoTemplate mongoTemplate;
    private final MessageRepository messageRepository;

    public MongoPlayground1Application(BookRepository bookRepository, Environment environment,
            MongoTemplate mongoTemplate, MessageRepository messageRepository) {
        this.bookRepository = bookRepository;
        this.environment = environment;
        this.mongoTemplate = mongoTemplate;
        this.messageRepository = messageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String mongodbUri = firstNonBlank(
                environment.getProperty("spring.data.mongodb.uri"),
                environment.getProperty("spring.mongodb.uri"));
        String mongodbDatabase = firstNonBlank(
                environment.getProperty("spring.data.mongodb.database"),
                environment.getProperty("spring.mongodb.database"));
        if ((mongodbDatabase == null || mongodbDatabase.isBlank()) && mongodbUri != null) {
            mongodbDatabase = new ConnectionString(mongodbUri).getDatabase();
        }
        log.info("Mongo effective config -> uri: {}, database: {}", mongodbUri,
                mongodbDatabase != null ? mongodbDatabase : "test (MongoDB default)");
        log.info("Mongo actual connected database -> {}", mongoTemplate.getDb().getName());

        System.out.println("MongoPlayground1Application started");
        Book book = new Book("Clean Code", "Robert C. Martin");
        // bookRepository.save(book);
        System.out.println("Book saved: " + book.getId());
        System.out.println("Book found: " + bookRepository.findAll().stream().map(b -> b.getId()).collect(Collectors.toList()));

        bookRepository.findAll(Pageable.ofSize(1).withPage(0)).map(b -> b.getId()).forEach(System.out::println);

        // System.out.println("MessageRepository found: " + messageRepository.count());

    }

    private String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return null;
    }
}
