package com.coding.practice.interview.questions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test13 {
  public static void main(String[] args) {
    Map<String, Integer> map2 = new HashMap<>();
    map2.put("anil", 1000);
    map2.put("ankit", 1200);
    map2.put("bhavna", 1200);
    map2.put("james", 1200);
    map2.put("micael", 1000);
    map2.put("tom", 1300);
    map2.put("daniel", 1300);
    map2.put("uma", 1500);

    Map<Integer, List<String>> map3 =
        map2.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(
                Collectors.groupingBy(
                    Map.Entry::getValue,
                    Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

    System.out.println(map3);

    map3.entrySet().stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
        //            .sorted(Comparator.comparing(Map.Entry::getKey).reversed())
        .skip(1)
        .findFirst()
        .ifPresentOrElse(
            e -> System.out.println("\n==============\n" + e),
            () -> System.out.println("Not found"));
  }
}
