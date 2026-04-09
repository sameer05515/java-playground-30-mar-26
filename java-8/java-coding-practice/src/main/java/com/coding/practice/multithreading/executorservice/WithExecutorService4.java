package com.coding.practice.multithreading.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Experimental code.
public class WithExecutorService4 {
  private static final int THREAD_POOL_SIZE = 5000;
  private static final int ELEMENTS = 500000;
  private static final int BATCH_SIZE = 10000;

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    long startTime = System.currentTimeMillis();

    ExecutorService executorService = Executors.newFixedThreadPool(ELEMENTS / BATCH_SIZE);
    List<Callable<Double>> callables = new ArrayList<>();
    double sum = 0;

    for (int i = 0; i < ELEMENTS; ) {
      int start = i;
      int end = start + BATCH_SIZE;
      callables.add(
          () -> {
            System.out.println(start + " to " + end);
            return sum(start, end);
          });
      i = end;
    }

    List<Future<Double>> futures = executorService.invokeAll(callables);

    for (Future<Double> f : futures) {
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

  public static double sum(int start, int end) throws ExecutionException, InterruptedException {
    double sum = 0;

    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    List<Callable<Integer>> callables = new ArrayList<>();
    for (int i = start + 1; i <= end; i++) {
      int finalI = i;
      callables.add(() -> TaskService.secTask(finalI));
    }

    List<Future<Integer>> futures = executorService.invokeAll(callables);

    for (Future<Integer> f : futures) {
      sum += f.get();
    }

    executorService.shutdown();
    try {
      executorService.awaitTermination(100, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    return sum;
  }
}
