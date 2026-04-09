package com.p.java8.multithreading;

// ChatGPT5 - ABC sequence using 3 threads (wait/notify)
public class ABCPrinter {
    private int turn = 0; // 0->A, 1->B, 2->C
    private final int times;

    public ABCPrinter(int times) {
        this.times = times;
    }

    public synchronized void print(char ch, int target) {
        for (int i = 0; i < times; i++) {
            while (turn != target) {
                try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            System.out.print(ch);
            turn = (turn + 1) % 3;
            notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 10; // ABC repeated n times
        ABCPrinter obj = new ABCPrinter(n);

        Thread t1 = new Thread(() -> obj.print('A', 0));
        Thread t2 = new Thread(() -> obj.print('B', 1));
        Thread t3 = new Thread(() -> obj.print('C', 2));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}