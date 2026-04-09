package com.coding.practice.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingEx1 {
  public static void main(String[] args) {
    // Data source: List of names
    List<String> names = Arrays.asList("Alice", "Bob", "Andrew", "Charlie", "Amanda");

    // Filtering and sorting using Stream API
    List<String> filteredAndSortedNames =
        names.stream()
            //            .filter(name -> name.startsWith("A")) // Filter names starting with 'A'
            .sorted(Comparator.reverseOrder()) // Sort alphabetically
            .collect(Collectors.toList()); // Collect the results into a list

    // Print the result
    System.out.println(filteredAndSortedNames);
  }
}
