package com.coding.practice.multithreading.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExampleUsingBlockingQueue {
  public static void main(String[] args) {

    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    Thread producerThread =
        new Thread(
            () -> {
              try {
                for (int i = 1; i <= 10; i++) {

                  //                  queue.add(i);
                  queue.put(i);
                  System.out.println("================  Produced: " + i);
                  Thread.sleep(1000);
                }
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            });

    Thread consumerThread =
        new Thread(
            () -> {
              try {
                for (int i = 0; i < 10; i++) {

                  //                  queue.poll();
                  System.out.println("-----\nSize of queue before take: " + queue.size());
                  Integer value = queue.take();
                  System.out.println("Size after take: " + queue.size());
                  System.out.println("Consumed: " + value);
                  Thread.sleep(1500);
                }
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            });
    producerThread.start();
    consumerThread.start();
  }
}
