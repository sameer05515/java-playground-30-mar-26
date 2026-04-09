package com.p.concurrency.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TableLockFree {

    private static final int COUNT = 100000;
    private static final int THREADS = 5;

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool(THREADS);

        int chunk = COUNT / THREADS;
        List<Future<String>> futures = new ArrayList<>();

        for (int t = 0; t < THREADS; t++) {
            int s = t * chunk + 1;
            int e = (t == THREADS - 1) ? COUNT : (t + 1) * chunk;

            futures.add(pool.submit(() -> computeChunk(s, e)));
        }

        // single-threaded print (no contention)
        StringBuilder finalOutput = new StringBuilder();
        for (Future<String> f : futures) {
            finalOutput.append(f.get());
        }

        System.out.print(finalOutput);

        pool.shutdown();

        long diff = System.currentTimeMillis() - start;
        System.out.println("\n---\nTime elapsed: " + diff);
    }

    private static String computeChunk(int start, int end) {
        StringBuilder sb = new StringBuilder(8192);

        for (int i = start; i <= end; i++) {
            for (int j = 1; j <= 10; j++) {
                sb.append("\t\t\t").append(i * j);
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}