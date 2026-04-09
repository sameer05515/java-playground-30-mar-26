package com.coding.practice.interview.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test10 {
  public static void main(String[] args) {
    // Separate odd and even numbers into two lists using Java 8.
    List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30, 35, 40);

    numbers.stream()
        .collect(Collectors.partitioningBy(e -> e % 2 == 0))
        .forEach((k, v) -> System.out.println(v));

    // ==============

    //    Merge & Sort -without duplicates
    Integer[] arr1 = {3, 5, 7, 9};
    Integer[] arr2 = {3, 4, 6, 9};

    // Merge & remove duplicates using Set
    Set<Integer> mergedSet = new TreeSet<>();
    mergedSet.addAll(Arrays.asList(arr1));
    mergedSet.addAll(Arrays.asList(arr2));

    System.out.println("mergedSet: " + mergedSet);

    // Convert back to List/Array
    List<Integer> sortedList = new ArrayList<>(mergedSet);
    System.out.println("sortedList: " + sortedList);

    System.out.println(
        Stream.concat(Stream.of(arr1), Stream.of(arr2))
            .distinct()
            .sorted()
            .map(String::valueOf)
            .collect(Collectors.joining(", ")));
    //            .forEach(System.out::println);
  }
}
