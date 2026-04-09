package com.p.java8.multithreading.abc;


class ABCPrinter {

    private int turn = 0; // 0 -> A, 1 -> B, 2 -> C

    public synchronized void printA() {
        try {
            while (true) {
                while (turn != 0) wait();
                System.out.print("A");
                turn = 1;
                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void printB() {
        try {
            while (true) {
                while (turn != 1) wait();
                System.out.print("B");
                turn = 2;
                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void printC() {
        try {
            while (true) {
                while (turn != 2) wait();
                System.out.print("C");
                turn = 0;
                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}





public class Main {
    public static void main(String[] args) {

        ABCPrinter printer = new ABCPrinter();

        new Thread(printer::printA).start();
        new Thread(printer::printB).start();
        new Thread(printer::printC).start();
    }
}