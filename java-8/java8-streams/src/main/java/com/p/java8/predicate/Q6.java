package com.p.java8.predicate;

import java.util.function.Predicate;

public class Q6 {
    public static void main(String[] args) {

        Predicate<Integer> p = x -> x > 10;

        Predicate<Integer> result = p.negate().negate().and(x -> x < 20);

        System.out.println(result.test(15)); // ?
        System.out.println(result.test(25)); // ?
    }
}