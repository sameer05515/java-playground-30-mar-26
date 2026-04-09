package com.p.java8.predicate;

import java.util.function.Predicate;

public class Q3 {
    public static void main(String[] args) {

        Predicate<String> p1 = s -> s.startsWith("A");
        Predicate<String> p2 = s -> s.endsWith("Z");

        Predicate<String> result = p1.or(p2).and(p1.negate());

        System.out.println(result.test("ABZ")); // ?
    }
}