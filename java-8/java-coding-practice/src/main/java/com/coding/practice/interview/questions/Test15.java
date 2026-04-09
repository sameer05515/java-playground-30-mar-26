package com.coding.practice.interview.questions;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;

public class Test15 {
  public static void main(String[] args) {
    Stream<String> stringStream =
        Stream.of("Prem", "Naren", "Rimi").map(String::toUpperCase).peek(System.out::println);
    //    System.out.println(stringStream);

    List<String> count =
        Stream.of("Prem", "Naren", "Rimi")
            .peek(System.out::println)
            .map(String::toUpperCase)
            .peek(System.out::println)
            .toList();
    System.out.println(count);

    Map<Integer, List<String>> collect =
        employees().stream()
            .collect(
                Collectors.toMap(employee -> employee.getName(), employee -> employee.getSalary()))
            .entrySet()
            .stream()
            .collect(
                Collectors.groupingBy(
                    Map.Entry::getValue,
                    Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
    System.out.println(collect);
    collect.entrySet().stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
        .skip(1)
        .findFirst()
        .ifPresentOrElse(e -> System.out.println(e), () -> System.out.println("No value"));
  }

  public static List<Employee> employees() {
    return List.of(
        Employee.builder().id(1).name("Prem").salary(1000).build(),
        Employee.builder().id(2).name("Prem2").salary(1100).build(),
        Employee.builder().id(3).name("Prem3").salary(1200).build(),
        Employee.builder().id(4).name("Prem4").salary(1200).build(),
        Employee.builder().id(5).name("Prem5").salary(1300).build(),
        Employee.builder().id(6).name("Prem6").salary(1400).build(),
        Employee.builder().id(7).name("Prem7").salary(1800).build(),
        Employee.builder().id(8).name("Prem8").salary(1700).build());
  }

  @Data
  @Builder
  public static class Employee {
    private int id;
    private String name;
    private int salary;
  }
}
