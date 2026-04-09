package com.coding.practice.multithreading.completablefuture;

import java.util.concurrent.TimeUnit;

public class Utility {
  public static void delay(long microseconds) {
    try {
      TimeUnit.MILLISECONDS.sleep(microseconds);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static String aTask(String str) {
    return str;
  }
}
