package com.p.concurrency.tables;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Table1 {

    private static final int COUNT = 100000;
    private static final int THREADS = 3;

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        int chunkSize = COUNT / THREADS;

        for (int t = 0; t < THREADS; t++) {
            int startIdx = t * chunkSize + 1;
            int endIdx = (t == THREADS - 1) ? COUNT : (t + 1) * chunkSize;

            executor.submit(() -> myTask(startIdx, endIdx));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long diff = System.currentTimeMillis() - start;

        System.out.println("\n---\nTime elapsed: " + diff);
    }

    public static void myTask(int start, int end) {
        for (int i = start; i <= end; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= 10; j++) {
                sb.append("\t\t\t").append(i * j);
            }
            System.out.println(sb);
        }
    }
}