package com.coding.practice.multithreading.executorservice;

public class WithManualThreads {
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    Thread[] threads = new Thread[10];
    for (int i = 1; i <= 10; i++) {
      int finalI = i;
      threads[i - 1] =
          new Thread(
              () -> {
                TaskService.aTask(finalI);
              });
      threads[i - 1].start();
    }

    for (int i = 0; i < 10; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - startTime));
  }
}
