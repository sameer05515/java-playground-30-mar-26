package com.p.java8.starcoding;

// ChatGPT5 - Hollow Square Pattern
public class HollowSquare {
    public static void main(String[] args) {
        int n = 5; // size

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (i == 1 || i == n || j == 1 || j == n) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }

            }
            System.out.println();
        }
    }
}