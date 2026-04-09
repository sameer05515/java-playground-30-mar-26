package com.coding.practice.multithreading.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WithExecutorService {
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();

    ExecutorService executorService = Executors.newFixedThreadPool(50);

    //    Future<Integer>[] futures = new Future<Integer>[100];

    for (int i = 1; i <= 100; i++) {
      int finalI = i;
      //      futures[i - 1] =
      executorService.submit(() -> TaskService.aTask(finalI));
    }

    executorService.shutdown();

    try {
      executorService.awaitTermination(100, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - startTime));
  }
}
