package com.coding.practice.multithreading.executorservice;

public class WithoutThreads {
  private static final int ELEMENTS = 100000;

  public static void main(String[] args) {

    long startTime = System.currentTimeMillis();

    double sum = 0;
    for (int i = 1; i <= ELEMENTS; i++) {
      sum += TaskService.secTask(i);
    }

    System.out.println("Sum: " + sum);

    System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - startTime));
  }
}
