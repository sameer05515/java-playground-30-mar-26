package com.p.java8.predicate;

import java.util.function.Predicate;

public class Q4 {
    public static void main(String[] args) {

        Predicate<Integer> p = x -> x > 5;

        p = p.and(x -> x < 20);

        System.out.println(p.test(10)); // ?
        System.out.println(p.test(25)); // ?
    }
}