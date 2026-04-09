package com.coding.practice.multithreading.evenoddprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Printer {
  private final Lock lock = new ReentrantLock();
  private final Condition numberTurn = lock.newCondition();
  private final Condition letterTurn = lock.newCondition();
  private boolean isNumberTurn = true; // Start with number

  public void printNumber(int num) throws InterruptedException {
    lock.lock();
    try {
      while (!isNumberTurn) {
        numberTurn.await();
      }
      System.out.print(num + " ");
      isNumberTurn = false;
      letterTurn.signal();
    } finally {
      lock.unlock();
    }
  }

  public void printLetter(char ch) throws InterruptedException {
    lock.lock();
    try {
      while (isNumberTurn) {
        letterTurn.await();
      }
      System.out.print(ch + " ");
      isNumberTurn = true;
      numberTurn.signal();
    } finally {
      lock.unlock();
    }
  }
}

public class SequencePrinter {
  public static void main(String[] args) {
    Printer printer = new Printer();

    Thread t1 =
        new Thread(
            () -> {
              for (int i = 1; i <= 3; i++) {
                try {
                  printer.printNumber(i);
                } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
                }
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              for (char ch = 'A'; ch <= 'C'; ch++) {
                try {
                  printer.printLetter(ch);
                } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
                }
              }
            });

    t1.start();
    t2.start();
  }
}
