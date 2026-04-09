package com.p.java8.multithreading.ab;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ABPrinter {

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean printA = true; // start with A

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

        Runnable taskA = () -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    while (!printA) {
                        condition.await();
                    }
                    System.out.print("A");
                    printA = false;
                    condition.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        };

        Runnable taskB = () -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    while (printA) {
                        condition.await();
                    }
                    System.out.print("B");
                    printA = true;
                    condition.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        };

        es.submit(taskA);
        es.submit(taskB);

        es.shutdown();
    }
}