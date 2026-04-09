package com.coding.practice.multithreading;

public class CounterTest {
  public static void main(String[] args) throws InterruptedException {

    Counter counter = new Counter();

    MyThread m1 = new MyThread("m1", counter);
    MyThread m2 = new MyThread("m2", counter);
    m1.start();
    m2.start();
    m1.join();
    m2.join();
    System.out.println(counter.getCounter());
  }

  private static class Counter {

    private int counter = 0;

    public void increment() {
      synchronized (this) {
        counter++;
      }
    }

    public int getCounter() {
      return counter;
    }
  }

  private static class MyThread extends Thread {
    private final Counter counter;

    public MyThread(String name, Counter counter) {
      super(name);
      this.counter = counter;
    }

    @Override
    public void run() {
      for (int i = 0; i < 10000; i++) {
        counter.increment();
      }
    }
  }
}
