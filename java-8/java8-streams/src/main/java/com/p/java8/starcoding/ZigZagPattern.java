package com.p.java8.starcoding;

// ChatGPT5 - ZigZag Pattern (3 rows)
public class ZigZagPattern {
    public static void main(String[] args) {
        int n = 16; // length

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= n; j++) {

                if ((i == 1 && j % 4 == 1) ||
                    (i == 2 && j % 2 == 0) ||
                    (i == 3 && j % 4 == 3)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
    }
}