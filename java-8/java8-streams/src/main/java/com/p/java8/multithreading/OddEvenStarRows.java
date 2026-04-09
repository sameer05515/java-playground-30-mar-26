package com.p.java8.multithreading;

// ChatGPT5 - Odd/Even Star Rows using 2 Threads (wait/notify)
public class OddEvenStarRows {

    private int row = 1;
    private final int n;

    public OddEvenStarRows(int n) {
        this.n = n;
    }

    public synchronized void printOdd() {
        while (row <= n) {
            while (row % 2 == 0) {
                try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            printRow(row);
            row++;
            notifyAll();
        }
    }

    public synchronized void printEven() {
        while (row <= n) {
            while (row % 2 != 0) {
                try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            printRow(row);
            row++;
            notifyAll();
        }
    }

    private void printRow(int r) {
        for (int i = 0; i < r; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        OddEvenStarRows obj = new OddEvenStarRows(n);

        Thread t1 = new Thread(obj::printOdd);
        Thread t2 = new Thread(obj::printEven);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}