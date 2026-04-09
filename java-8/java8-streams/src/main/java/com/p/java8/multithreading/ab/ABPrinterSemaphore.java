package com.p.java8.multithreading.ab;

import java.util.concurrent.*;

public class ABPrinterSemaphore {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

        Semaphore semA = new Semaphore(1); // A starts
        Semaphore semB = new Semaphore(0);

        Runnable taskA = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    semA.acquire();
                    System.out.print("A");
                    semB.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable taskB = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    semB.acquire();
                    System.out.print("B");
                    semA.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        es.submit(taskA);
        es.submit(taskB);

        es.shutdown();
    }
}