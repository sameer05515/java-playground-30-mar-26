package com.p.java8.starcoding;

// ChatGPT5 - Concentric Square Pattern
public class ConcentricSquare {
    public static void main(String[] args) {
        int n = 5; // change value

        int size = 2 * n - 1;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int top = i;
                int left = j;
                int bottom = size - 1 - i;
                int right = size - 1 - j;

                int min = Math.min(Math.min(top, bottom), Math.min(left, right));

                System.out.print(n - min);
            }
            System.out.println();
        }
    }
}