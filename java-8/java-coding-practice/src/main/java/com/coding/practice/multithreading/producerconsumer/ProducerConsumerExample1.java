package com.coding.practice.multithreading.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample1 {
  int capacity;
  Queue<Integer> queue = new LinkedList<>();

  ProducerConsumerExample1(int capacity) {
    this.capacity = capacity;
  }

  public synchronized void produce(int value) throws InterruptedException {
    while (queue.size() == capacity) {
      wait(); // wait until there is space in the queue
    }

    queue.add(value);
    System.out.println("Produced: " + value);
    notifyAll(); // notify consumers that an item has been produced
  }

  public synchronized int consume() throws InterruptedException {
    while (queue.isEmpty()) {
      wait();
    }

    int value = queue.poll();
    System.out.println("Consumed: " + value);
    notifyAll();
    return value;
  }

  public static void main(String[] args) {
    ProducerConsumerExample1 pc = new ProducerConsumerExample1(5);
    Thread producerThread =
        new Thread(
            () -> {
              try {
                for (int i = 0; i < 10; i++) {
                  pc.produce(i);
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
                  pc.consume();
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
