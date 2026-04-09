package com.coding.practice.interview.questions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test14 {
  public static void main(String[] args) {
    Map<Integer, String> students = new HashMap<>();

    students.put(1, "Prem");
    students.put(2, "Prem");
    students.put(3, "Naren");
    students.put(4, "Vinita");

    Map<String, Long> nameOccMap =
        students.entrySet().stream()
            .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));
    System.out.println(nameOccMap);
    List<Map.Entry<String, Long>> filtered =
        nameOccMap.entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            //              .map(entry -> entry.getKey())
            .collect(Collectors.toList());
    System.out.println(filtered);
  }
}
