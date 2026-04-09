package com.coding.practice.multithreading;

import java.util.*;
import java.util.concurrent.*;

public class MapThreadDemo {
  public static void main(String[] args) throws InterruptedException {

    // 1️⃣ Synchronized Map
    Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

    // 2️⃣ ConcurrentHashMap
    Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

    System.out.println("=== Testing SynchronizedMap ===");
    runTest(syncMap);

    System.out.println("\n=== Testing ConcurrentHashMap ===");
    runTest(concurrentMap);
  }

  private static void runTest(Map<String, Integer> map) throws InterruptedException {
    Runnable writer =
        () -> {
          for (int i = 0; i < 1000; i++) {
            map.put(Thread.currentThread().getName() + "-" + i, i);
          }
        };

    Runnable reader =
        () -> {
          for (int i = 0; i < 1000; i++) {
            // With synchronizedMap, we must manually synchronize during iteration
            synchronized (map) {
              for (String key : map.keySet()) {
                // Simulate read
                map.get(key);
              }
            }
          }
        };

    Thread t1 = new Thread(writer, "Writer-1");
    Thread t2 = new Thread(writer, "Writer-2");
    Thread t3 = new Thread(reader, "Reader-1");

    long start = System.currentTimeMillis();

    t1.start();
    t2.start();
    t3.start();
    t1.join();
    t2.join();
    t3.join();

    long end = System.currentTimeMillis();
    System.out.println("Final size: " + map.size() + ", Time taken: " + (end - start) + " ms");
  }
}
