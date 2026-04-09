package com.coding.practice.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
  public static void main(String[] args) {
    ReentrantLockTest test = new ReentrantLockTest();
    test.outerMethod();
  }

  Lock lock = new ReentrantLock();

  //  ReentrantReadWriteLock

  public void outerMethod() {
    lock.lock();
    try {
      System.out.println("Outer Method");
      innerMethod();
    } finally {
      lock.unlock();
    }
  }

  public void innerMethod() {
    lock.lock();
    try {
      System.out.println("Inner Method");
      // outerMethod();
    } finally {
      lock.unlock();
    }
  }
}
