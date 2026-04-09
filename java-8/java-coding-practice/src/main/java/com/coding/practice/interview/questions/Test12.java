package com.coding.practice.interview.questions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test12 {
  public static void main(String[] args) {
    // find occurrence of each characters in given word
    String s = "ilovemyindiayehmeraindia";
    findOccurrenceOfCharacters1(s);
    findOccurrenceOfCharacters2(s);

    // find all duplicate element from a given String
    findCharacterHavingOccCountGreaterThanOne1(s);
    findCharacterHavingOccCountGreaterThanOne2(s);

    // find Unique Characters
    findUniqueCharacters(s);

    // find first non-repeat element from given string
    // to preserve insertion order, we need to use LinkedHashmap in place of default HashMap
    // implementation in Collectors.groupingBy
    findFirstNonRepeatChar1(s);
    findFirstNonRepeatChar2(s);

    // find nth repeat character from given string
    findNthRepeatedChar(s, 3);

    // second-highest number from given array
    int[] numbers = {1, 2, 3, 4, 5, 11, 21, 123};
    nthHighestNumber(numbers, 2);

    // longest string from given array
    String[] strings = {"java", "spring-boot", "reactjs", "microservices"};
    longestString(strings);

    // find elements from array which starts with 1
    elementsStartsWithOne(numbers);

    // String.join example
    header("String.join example");
    System.out.println(String.join("-", Arrays.asList("1", "2", "3")));
    // skip and limit method use case example
    header("skip and limit method use case example");
    IntStream.rangeClosed(1, 10).skip(1).limit(8).forEach(System.out::println);

    // sort a list or map
    // map and flatMap example

    // 🔥 Asked in Myntra | Find Nth Highest Salary Using Java Streams API
    Map<String, Integer> map = new HashMap<>();
    map.put("Prem", 1000);
    map.put("Prem2", 2000);
    map.put("Prem3", 3000);
    map.put("Prem4", 4000);
    map.put("Prem5", 5000);
    map.put("Prem6", 6000);
    map.put("Prem7", 7000);
    map.put("Prem8", 2000);

    nthHighestSalary(3, map);
  }

  private static void nthHighestSalary(int num, Map<String, Integer> map) {

    Map.Entry<Integer, List<String>> integerListEntry =
        map.entrySet().stream()
            .collect(
                Collectors.groupingBy(
                    Map.Entry::getValue,
                    Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
            .collect(Collectors.toList())
            .get(num - 1);
    System.out.println(integerListEntry);
  }

  private static void header(String header) {
    System.out.println("================ " + header + " ================");
  }

  public static void findOccurrenceOfCharacters1(String s) {
    header("find occurrence of each characters in given word using chars method");
    s.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
        .forEach((k, v) -> System.out.println(k + " : " + v));
  }

  public static void findOccurrenceOfCharacters2(String s) {
    header("find occurrence of each characters in given word using split method");
    Arrays.stream(s.split(""))
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
        .forEach((k, v) -> System.out.println(k + " : " + v));
  }

  public static void findCharacterHavingOccCountGreaterThanOne1(String s) {
    header("find all duplicate element from a given String");
    Arrays.stream(s.split(""))
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
  }

  public static void findCharacterHavingOccCountGreaterThanOne2(String s) {
    header("find all duplicate elements from a given String");
    List<String> list =
        Arrays.stream(s.split(""))
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    System.out.println(list);
  }

  public static void findUniqueCharacters(String s) {
    header("find all unique elements from a given String");
    List<String> list =
        Arrays.stream(s.split(""))
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    System.out.println(list);
  }

  public static void findFirstNonRepeatChar1(String s) {
    header("find first non-repeat element from given string");
    header(
        "to preserve insertion order, we need to use LinkedHashmap in place of default HashMap implementation in Collectors.groupingBy");

    Arrays.stream(s.split(""))
        .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() == 1)
        .map(Map.Entry::getKey)
        .findFirst()
        .ifPresentOrElse(
            c -> System.out.println("First non-repeat character is: '" + c + "'"),
            () -> System.out.println("No non-repeat character found"));

    //                    .collect(Collectors.toList());
  }

  public static void findFirstNonRepeatChar2(String s) {
    header("find first non-repeat element from given string");
    header(
        "to preserve insertion order, we need to use LinkedHashmap in place of default HashMap implementation in Collectors.groupingBy");

    String firstNonRepeatKey =
        Arrays.stream(s.split(""))
            .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() == 1)
            .findFirst()
            .get()
            .getKey();
    System.out.println("firstNonRepeatKey : " + firstNonRepeatKey);
  }

  private static void findNthRepeatedChar(String s, int n) {
    header("find nth repeat character from given string");
    header(
        "to preserve insertion order, we need to use LinkedHashmap in place of default HashMap implementation in Collectors.groupingBy");

    Arrays.stream(s.split(""))
        .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .skip(n - 1)
        .findFirst()
        .ifPresentOrElse(
            c -> System.out.println(n + "th repeat character is: '" + c.getKey() + "'"),
            () -> System.out.println("No " + n + "th repeat character found"));
  }

  private static void nthHighestNumber(int[] numbers, int n) {
    header("second-highest number from given array");
    Arrays.stream(numbers)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .skip(n - 1)
        .findFirst()
        .ifPresentOrElse(
            c -> System.out.println(n + "th highest number is: '" + c + "'"),
            () -> System.out.println("No " + n + "th highest number found"));
  }

  private static void longestString(String[] strings) {
    header("longest string from given array");

    String longestString =
        Arrays.stream(strings).reduce("", (prev, s) -> s.length() > prev.length() ? s : prev);
    System.out.println("Longest string: " + longestString);
  }

  private static void elementsStartsWithOne(int[] numbers) {
    header("find elements from array which starts with 1");
    System.out.println(
        Arrays.stream(numbers)
            .boxed()
            .map(num -> num + "")
            .filter(str -> str.startsWith("1"))
            .collect(Collectors.toList()));
  }
}
