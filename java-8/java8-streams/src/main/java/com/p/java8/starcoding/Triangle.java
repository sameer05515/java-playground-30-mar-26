package com.p.java8.starcoding;


public class Triangle {
    public static void main(String[] args) {
        int r = 11;
        for (int i = 1; i <= r; i++) {

            for (int j = 1; j <= (r-i); j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i*2-1; j++) {
                System.out.print(". ");
            }
//            for (int j = 0; j <= (r-i); j++) {
//                System.out.print("  ");
//            }
            System.out.println();
        }
        for (int i = r-1; i >= 0; i--) {

            for (int j = 1; j <= (r-i); j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i*2-1; j++) {
                System.out.print(". ");
            }
//            for (int j = 0; j <= (r-i); j++) {
//                System.out.print("  ");
//            }
            System.out.println();
        }
    }
}