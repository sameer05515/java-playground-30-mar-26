package com.coding.practice.multithreading.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WithExecutorService2 {

  private static final int THREAD_POOL_SIZE = 5000;
  private static final int ELEMENTS = 100000;

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    long startTime = System.currentTimeMillis();

    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    List<Future<Integer>> futures = new ArrayList<>();

    for (int i = 1; i <= ELEMENTS; i++) {
      int finalI = i;
      Future<Integer> future = executorService.submit(() -> TaskService.secTask(finalI));
      futures.add(future);
    }

    double sum = 0;
    for (Future<Integer> f : futures) {
      sum += f.get();
    }

    executorService.shutdown();

    try {
      executorService.awaitTermination(100, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println("Total sum: " + sum);
    System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - startTime));
  }
}
