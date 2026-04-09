package com.p.concurrency.oddeven;

class OddEven {
    static int num = 1;
    static final int MAX = 10;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> printOdd()).start();
        new Thread(() -> printEven()).start();
    }

    static void printOdd() {
        while (num <= MAX) {
            synchronized (lock) {
                while (num % 2 == 0) {
                    try { lock.wait(); } catch (Exception e) {}
                }
                System.out.print(num + " ");
                num++;
                lock.notify();
            }
        }
    }

    static void printEven() {
        while (num <= MAX) {
            synchronized (lock) {
                while (num % 2 != 0) {
                    try { lock.wait(); } catch (Exception e) {}
                }
                System.out.print(num + " ");
                num++;
                lock.notify();
            }
        }
    }
}