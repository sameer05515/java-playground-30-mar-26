package com.p.java8.predicate;

import java.util.function.Predicate;

public class Q1 {
    public static void main(String[] args) {

        Predicate<Integer> p1 = x -> x > 5;
        Predicate<Integer> p2 = x -> x % 2 == 0;

        Predicate<Integer> result1 = p1.and(p2).negate();
        Predicate<Integer> result2 = p1.and(p2.negate());

        System.out.println(result1.test(8)); // ?
        System.out.println(result2.test(8)); // ?
    }
}