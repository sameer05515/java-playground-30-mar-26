package com.coding.practice.interview.questions;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchDemo {
  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    int numberOfServices = 3;
    ExecutorService service = Executors.newFixedThreadPool(numberOfServices);
    CountDownLatch countDownLatch = new CountDownLatch(numberOfServices);
    service.submit(new DependentService((countDownLatch)));
    service.submit(new DependentService((countDownLatch)));
    service.submit(new DependentService((countDownLatch)));

    countDownLatch.await();
    System.out.println("Main resumed");
    service.shutdown();

    System.out.println("Time elapsed: " + (System.currentTimeMillis() - start) + " ms.");
  }
}

class DependentService implements Callable<String> {

  private final CountDownLatch countDownLatch;

  public DependentService(CountDownLatch countDownLatch) {
    this.countDownLatch = countDownLatch;
  }

  @Override
  public String call() throws Exception {
    System.out.println(Thread.currentThread().getName() + " service started.");
    Thread.sleep(1000);
    countDownLatch.countDown();
    return "ok";
  }
}
