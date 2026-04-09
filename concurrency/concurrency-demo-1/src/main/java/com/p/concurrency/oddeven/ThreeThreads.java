package com.p.concurrency.oddeven;

class ThreeThreads {
    static int num = 1;
    static final int MAX = 30;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(ThreeThreads::printOne).start();
        new Thread(ThreeThreads::printTwo).start();
        new Thread(ThreeThreads::printThree).start();
    }

    static void printOne() {
        while (true) {
            synchronized (lock) {
                while (num % 3 != 1 && num <= MAX) {
                    try { lock.wait(); } catch (Exception e) {}
                }
                if (num > MAX) break;
                System.out.print("T1:" + num + " ");
                num++;
                lock.notifyAll();
            }
        }
    }

    static void printTwo() {
        while (true) {
            synchronized (lock) {
                while (num % 3 != 2 && num <= MAX) {
                    try { lock.wait(); } catch (Exception e) {}
                }
                if (num > MAX) break;
                System.out.print("T2:" + num + " ");
                num++;
                lock.notifyAll();
            }
        }
    }

    static void printThree() {
        while (true) {
            synchronized (lock) {
                while (num % 3 != 0 && num <= MAX) {
                    try { lock.wait(); } catch (Exception e) {}
                }
                if (num > MAX) break;
                System.out.print("T3:" + num + " ");
                num++;
                lock.notifyAll();
            }
        }
    }
}