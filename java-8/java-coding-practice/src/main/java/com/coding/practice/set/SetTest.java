package com.coding.practice.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
  public static void main(String[] args) {
    Set<String> strings = new HashSet<>();

    strings.add("Prem");
    strings.add("Naren");
    strings.add("Vinita");

    Set<String> strings1 = new LinkedHashSet<>();
    strings1.add("Prem");
    strings1.add("Naren");
    strings1.add("Vinita");
    strings1.add("Prem");
    strings1.add("Prem");

    Set<String> strings2 = new TreeSet<>();
    strings2.add("Prem");
    strings2.add("Naren");
    strings2.add("Vinita");
    strings2.add("Prem");
    strings2.add("Prem");

    System.out.println(strings);
    System.out.println("---------------------");
    System.out.println(strings1);
    System.out.println("---------------------");
    System.out.println(strings2);
  }
}
