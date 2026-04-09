package com.p.java8.predicate;

import java.util.function.Predicate;

public class Q5 {
    public static void main(String[] args) {

        Predicate<String> p = Predicate.isEqual(null);

        System.out.println(p.test(null));   // ?
        System.out.println(p.test("ABC"));  // ?
    }
}