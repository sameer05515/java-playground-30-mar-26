package com.coding.practice.interview.questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

public class Test16 {
  public static void main(String[] args) {
    // Write a Java 8 program to find the second highest number in a list of integers using streams.
    Arrays.stream(new int[] {1, 2, 3, 4, 5})
        .boxed()
        .distinct()
        .sorted(Comparator.reverseOrder())
        .skip(1)
        .findFirst()
        .ifPresentOrElse(System.out::println, () -> System.out.println("No value found"));

    // Create Employee, Department Classes. Use Streams to give each department Average Employee
    // Salary

    System.out.println(
        employees().stream()
            .collect(
                Collectors.groupingBy(
                    e -> e.getDepartment().getName(),
                    Collectors.averagingDouble(Employee::getSalary))));
  }

  private static List<Employee> employees() {
    Department sales = Department.builder().name("Sales").id(1).build();
    Department it = Department.builder().name("IT").id(2).build();
    return List.of(
        Employee.builder().id(1).name("Prem1").department(sales).salary(1000).build(),
        Employee.builder().id(1).name("Prem2").department(sales).salary(2000).build(),
        Employee.builder().id(1).name("Prem3").department(it).salary(3000).build(),
        Employee.builder().id(1).name("Prem4").department(it).salary(4000).build());
  }

  @Data
  @Builder
  private static class Employee {
    private int id;
    private String name;
    private int salary;
    private Department department;
  }

  @Data
  @Builder
  private static class Department {
    private int id;
    private String name;
  }
}
