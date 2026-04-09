package com.coding.practice.interview.questions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test21 {
  private boolean numberTurn = true;

  public static void main(String[] args) {
    //        Suppose you have 2 threads. One of
    //        them prints (1,2,3.) and the other one
    //        prints (A,B,C,.).
    //
    //        How will you ensure that they run in a sequence so that it prints (1,A,2,B)?

    Test21 printerWaitNotify = new Test21();
    Thread t1 =
        new Thread(
            () -> {
              for (int i = 1; i <= 3; i++) {
                try {
                  printerWaitNotify.printNumber(i);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              for (char ch = 'A'; ch <= 'C'; ch++) {
                try {
                  printerWaitNotify.printLetter(ch);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    t1.start();
    t2.start();

    String s = "premendra";

    Map<Character, Long> map =
        s.chars()
            .mapToObj(ch -> (char) ch)
            .collect(
                Collectors.groupingBy(
                    Function.identity(), LinkedHashMap::new, Collectors.counting()));

    System.out.println(map);

    Optional<Map.Entry<Character, Long>> first =
        map.entrySet().stream().filter(e -> e.getValue() > 1).findFirst();

    System.out.println(first.get());
  }

  public synchronized void printNumber(int num) throws InterruptedException {
    while (!numberTurn) {
      wait();
    }
    System.out.print(num + " ");
    numberTurn = false;
    notifyAll();
  }

  public synchronized void printLetter(char ch) throws InterruptedException {
    while (numberTurn) {
      wait();
    }
    System.out.print(ch + " ");
    numberTurn = true;
    notifyAll();
  }

  public void t1() {
    synchronized (this) {
      for (int i = 1; i <= 3; i++) {
        System.out.println(i);
        try {
          wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public void t2() {
    synchronized (this) {
      for (int i = 1; i <= 3; i++) {
        System.out.println(i);
        try {
          wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}
