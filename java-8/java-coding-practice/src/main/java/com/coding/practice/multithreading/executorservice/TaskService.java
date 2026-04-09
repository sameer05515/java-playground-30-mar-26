package com.coding.practice.multithreading.executorservice;

public class TaskService {
  public static void aTask(int i) {
    try {
      Thread.sleep(2);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Will compute factorial of : " + i);
  }

  public static int secTask(int i) {
    try {
      //      Thread.sleep(i > 0 ? i : 1);
      Thread.sleep(1);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return i;
  }
}
