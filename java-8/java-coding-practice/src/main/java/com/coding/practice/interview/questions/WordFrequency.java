package com.coding.practice.interview.questions;

import java.util.*;
import java.util.stream.*;

public class WordFrequency {
  public static void main(String[] args) {
    List<String> words =
        List.of(
            "avocado", "apple", "banana", "apple", "apple", "orange", "banana", "apple", "banana",
            "kiwi", "avocado", "avocado", "banana");

    // Count occurrences
    Map<String, Long> freqMap =
        words.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

    // Sort by frequency (ascending) and print
    freqMap.entrySet().stream()
        .sorted(
            Map.Entry.<String, Long>comparingByValue().thenComparing(Map.Entry.comparingByKey()))
        .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
  }
}
