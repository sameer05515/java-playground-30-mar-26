package com.coding.practice.interview.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <a href="https://www.youtube.com/watch?v=tT3gUj6cq2U">Top 50+ Tricky Java Streams Coding
 * Interview Questions You Must Know to Crack Your Next Interview!</a>
 */
public class Test17 {

  public static void main(String[] args) {
    List<Integer> integers = List.of(1, 2, 3, 4, 1, 2, 3, 4, 5, 7, 6, 11, 121, 21);

    // Remove duplicates from a list and preserve the order
    header("Remove duplicates from a list and preserve the order");
    List<Integer> output = integers.stream().distinct().collect(Collectors.toList());
    System.out.println(output);

    // Remove duplicates from a list and no need to preserve the order
    header("Remove duplicates from a list and no need to preserve the order");
    System.out.println(integers.stream().collect(Collectors.toSet()));

    // Find all numbers starting with 1
    header("Find all numbers starting with 1");
    System.out.println(
        integers.stream()
            .filter(integer -> ("" + integer).startsWith("1"))
            .collect(Collectors.toList()));

    // join two streams
    List<Integer> list1 = List.of(1, 2, 3, 7);
    List<Integer> list2 = List.of(4, 5, 6, 8);
    header("join two streams");
    System.out.println(Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList()));

    // Merge 2 sorted lists into a single sorted list
    header("Merge 2 sorted lists into a single sorted list");
    System.out.println(
        Stream.concat(list1.stream(), list2.stream()).sorted().collect(Collectors.toList()));

    // check if a list of integers contains a prime number
    header("check if a list of integers contains a prime number");
    boolean b = list1.stream().anyMatch(Test17::isPrime);
    System.out.println(b ? "Contains prime number" : "no prime number in list");

    // print all prime numbers in list
    header("print all prime numbers in list");
    System.out.println(list1.stream().filter(Test17::isPrimeJava8).collect(Collectors.toList()));

    // how to debug streams using peek
    header("how to debug streams using peek");
    list1.stream()
        .peek(s -> System.out.println(s))
        .filter(integer -> ("" + integer).startsWith("1"))
        .collect(Collectors.toList())
        .forEach(s -> System.out.println("----->" + s));

    // Given a lst of strings,find out those strings which starts with a number.
    header("Given a lst of strings,find out those strings which starts with a number.");
    List<String> fruits = List.of("1apple", "banana", "avacado");
    System.out.println(
        fruits.stream()
            .filter(str -> Character.isDigit(str.charAt(0)))
            .collect(Collectors.toList()));

    // check given string is palindrome or not
    header("check given string is palindrome or not");
    String palindrome = "abcdcba";
    boolean isPalindrome =
        IntStream.rangeClosed(0, palindrome.length() / 2)
            .allMatch(i -> palindrome.charAt(i) == palindrome.charAt(palindrome.length() - 1 - i));
    System.out.println("Is palindrome: " + isPalindrome);

    // sort the list of decimals in reverse order
    header("sort the list of decimals in reverse order");
    System.out.println(
        List.of(1.0, 3.6, 1.1, 4.5).stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList()));

    // nth smallest integer in an array
    header("nth smallest integer in an array");
    System.out.println(
        Arrays.stream(new int[] {1, 2, 3, 4, 5, 6})
            .boxed()
            .distinct()
            .sorted(Comparator.reverseOrder())
            .skip(2)
            .findFirst()
            .get());

    // given a list of strings, join the strings with '[' as prefix, ']' as suffix and ',' as
    // delimiter
    header(
        "given a list of strings, join the strings with '[' as prefix, ']' as suffix and ',' as delimiter");
    System.out.println(fruits.stream().collect(Collectors.joining(", ", "[", "]")));

    // sum of first 2 numbers
    header("sum of first 2 numbers");
    System.out.println(list1.stream().limit(2).mapToInt(Integer::intValue).sum());

    // multiplication of first 2 numbers
    header("multiplication of first 2 numbers");
    System.out.println(List.of(1, 2, 3).stream().limit(2).reduce(1, (a1, b1) -> a1 * b1));
    System.out.println(list1.stream().limit(2).reduce(1, (a1, b1) -> a1 * b1));

    // find the sum of unique numbers
    header("find the sum of unique numbers");
    System.out.println(List.of(1, 2, 3, 4, 4, 5).stream().distinct().reduce(0, Integer::sum));

    // Find the words with k vowels
    header("Find the words with k=3 vowels");
    System.out.println(
        Arrays.stream("I love my India".split(" "))
            .filter(
                w -> {
                  long count =
                      w.chars()
                          .mapToObj(ch -> (char) ch)
                          .filter(ch -> "aeiouAEIOU".indexOf(ch) != -1)
                          .count();
                  return count == 3;
                })
            .collect(Collectors.toList()));

    // Given a string, find the first non-repeated character in it
    header("Given a string, find the first non-repeated character in it");
    String sample = "ilovemyindia";
    String firstNonRepeat =
        Arrays.stream(sample.split(""))
            .collect(Collectors.groupingBy(ch -> ch, LinkedHashMap::new, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(e -> e.getValue() == 1)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null);
    System.out.println(firstNonRepeat); // prints "l"

    /**
     * Given a list of integers, find
     *
     * <p>the minimum number
     *
     * <p>the maximum number
     *
     * <p>the sum of all numbers
     *
     * <p>the average of all numbers
     *
     * <p>the count of all numbers
     */
    header(
"""
Given a list of integers, find
the minimum number
the maximum number
the sum of all numbers
the average of all numbers
the count of all numbers
""");
    IntSummaryStatistics intSummaryStatistics =
        IntStream.of(1, 2, 3, 3, 4, 4, 6, 7, 1, 5).summaryStatistics();
    System.out.println(intSummaryStatistics);

    // Find the word with third-highest length
    header("Find the word with third highest length");
    Map<Integer, List<String>> mapOfLengthAndStrings =
        Arrays.asList(
                "c", "python", "zython", "java", "springboot", "reactjs", "microservices", "lava")
            .stream()
            .collect(Collectors.groupingBy(String::length));
    List<Map.Entry<Integer, List<String>>> thirdHighestEntry =
        mapOfLengthAndStrings.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
            .skip(3 - 1)
            .limit(1)
            .collect(Collectors.toList());
    System.out.println(mapOfLengthAndStrings);
    System.out.println(thirdHighestEntry);

    // Given a list of transactions, find the sum of transaction amount for each day
    header("Given a list of transactions, find the sum of transaction amount for each day");
    List<Transaction> transactions =
        List.of(
            new Transaction("2025-04-20", 500),
            new Transaction("2025-04-20", 600),
            new Transaction("2025-04-21", 1500));
    Map<String, Double> stringDoubleMap =
        transactions.stream()
            .collect(
                Collectors.groupingBy(
                    Transaction::getDate, Collectors.summingDouble(Transaction::getAmount)));
    System.out.println(stringDoubleMap);

    // Given an integer array, return true if array contains duplicate elements
    header("Given an integer array, return true if array contains duplicate elements");
    boolean containsDuplicateElements =
        Arrays.asList(1, 2, 3, 4, 1).stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .anyMatch(entry -> entry.getValue() > 1);
    System.out.println("list containing duplicate elements: " + containsDuplicateElements);

    // print the count of each character in a string
    header("print the count of each character in a string");
    System.out.println(
        Arrays.stream("hello world".split(""))
            .filter(s -> !s.isBlank())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

    // find the first odd number from a given list
    header("find the first odd number from a given list");
    System.out.println(
        List.of(1, 2, 3, 4, 5).stream().filter(num -> num % 2 != 0).findFirst().get());

    // find the intersection of two lists, common elements
    header("find the intersection of two lists, common elements");
    List<Integer> interSectionList1 = List.of(1, 2, 3, 4, 5);
    List<Integer> interSectionList2 = List.of(3, 4, 5, 6, 7);
    List<Integer> intersection = new ArrayList<>(interSectionList1);
    intersection.retainAll(interSectionList2);
    System.out.println(intersection);
    System.out.println(interSectionList1.stream().filter(interSectionList2::contains).toList());

    // Average age of students
    header("Average age of students");
    System.out.println(List.of(20, 30, 40, 50).stream().mapToInt(i -> i).average().orElse(0.0));

    // sort by multiple fields
    header("sort by multiple fields");
    List<Student> students =
        List.of(
            new Student(28, "Prem"),
            new Student(29, "Prem1"),
            new Student(30, "Prem2"),
            new Student(28, "Naren"),
            new Student(39, "Naren1"),
            new Student(40, "Naren2"));
    System.out.println(
        students.stream()
            .sorted(
                Comparator.comparing(Student::getAge).reversed().thenComparing(Student::getName))
            .toList());

    // count each word from the given arraylist
    header("count each word from the given arraylist");
    System.out.println(
        Arrays.asList("india is great india is land of diversity".split(" ")).stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

    // Longest string in a list of string
    header("Longest string in a list of string");
    List<String> courses = List.of("java", "reactjs", "spring-boot", "microservices");
    System.out.println(
        courses.stream().reduce("", (acc, str) -> acc.length() > str.length() ? acc : str));
    System.out.println(courses.stream().max(Comparator.comparingInt(String::length)).orElse(""));
  }

  public static void header(String header) {
    System.out.println("=============  " + header + "  =======================");
  }

  private static boolean isPrime(int num) {
    for (int i = 2; i < num; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }

  private static boolean isPrimeJava8(int num) {
    if (num < 1) {
      return false;
    }
    return IntStream.rangeClosed(2, (int) Math.sqrt(num)).noneMatch(i -> num % i == 0);
  }

  private static class Transaction {
    private final String date;
    private final double amount;

    public Transaction(String date, double amount) {
      this.amount = amount;
      this.date = date;
    }

    public String getDate() {
      return date;
    }

    public double getAmount() {
      return amount;
    }
  }

  private static class Student {
    private int age;
    private String name;

    public Student(int age, String name) {
      this.age = age;
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "Student{" + "age=" + age + ", name='" + name + '\'' + '}';
    }
  }
}
