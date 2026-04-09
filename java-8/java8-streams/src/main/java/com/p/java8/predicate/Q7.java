package com.p.java8.predicate;

import java.util.function.Predicate;

public class Q7 {
    public static void main(String[] args) {

        Predicate<Integer> p1 = x -> {
            System.out.println("p1");
            return x > 5;
        };
        Predicate<Integer> p2 = x -> {
            System.out.println("p2");
            return x < 15;
        };
        Predicate<Integer> p3 = x -> {
            System.out.println("p3");
            return x % 2 == 0;
        };

        Predicate<Integer> result = p1.and(p2).or(p3);

        System.out.println(result.test(4));  // ?
        System.out.println(result.test(7));  // ?
    }
}