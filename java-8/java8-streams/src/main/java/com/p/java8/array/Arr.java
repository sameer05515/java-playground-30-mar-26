package com.p.java8.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Arr {

    private static final int TOTAL_TASKS = 1_000_000;

    public static void main(String[] args) throws InterruptedException {

        int[] a = {2, 3, 4, 9};
        int[] b = {2, 3, 7};
        int[] c = {2, 3, 1};

        Set<Integer> common= IntStream.of(a).boxed().collect(Collectors.toSet());

        common.retainAll(IntStream.of(b).boxed().collect(Collectors.toSet()));
        common.retainAll(IntStream.of(c).boxed().collect(Collectors.toSet()));

        System.out.println(common);

        Set<Integer> union=arrToSet(a);
        union.addAll(arrToSet(b));
        union.addAll(arrToSet(c));

        System.out.println(union);

    }

    private static Set<Integer> arrToSet(int[] arr){
        return IntStream.of(arr).boxed().collect(Collectors.toSet());
    }
}