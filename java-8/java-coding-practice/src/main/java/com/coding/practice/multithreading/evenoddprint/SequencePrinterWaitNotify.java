package com.coding.practice.multithreading.evenoddprint;

class PrinterWaitNotify {
  private boolean numberTurn = true; // Start with numbers

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
}

public class SequencePrinterWaitNotify {
  public static void main(String[] args) {
    PrinterWaitNotify printerWaitNotify = new PrinterWaitNotify();

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
  }
}
