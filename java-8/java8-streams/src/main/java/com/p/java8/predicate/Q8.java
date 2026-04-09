package com.p.java8.predicate;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Q8 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(3, 7, 12, 18, 21);

        Predicate<Integer> p = x -> x > 10;
        Predicate<Integer> q = x -> x % 3 == 0;

        List<Integer> result = list.stream()
                .filter(p.and(q.negate()))
                .collect(Collectors.toList());

        System.out.println(result); // ?
    }
}