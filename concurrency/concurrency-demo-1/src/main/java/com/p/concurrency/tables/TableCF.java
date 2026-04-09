package com.p.concurrency.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TableCF {

    private static final int COUNT = 100000;
    private static final int THREADS = 3;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        int chunk = COUNT / THREADS;
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int t = 0; t < THREADS; t++) {
            int s = t * chunk + 1;
            int e = (t == THREADS - 1) ? COUNT : (t + 1) * chunk;

            futures.add(CompletableFuture.runAsync(() -> myTask(s, e)));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

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