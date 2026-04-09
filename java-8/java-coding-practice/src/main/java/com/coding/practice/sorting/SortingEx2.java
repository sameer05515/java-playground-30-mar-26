package com.coding.practice.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingEx2 {
  private static class Comp implements Comparable<Comp> {
    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    private int id;
    private String name;

    Comp(int id, String name) {
      this.id = id;
      this.name = name;
    }

    @Override
    public String toString() {
      return "Emp{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    public int compareTo(Comp o) {
      return Integer.compare(this.id, o.id);
    }
  }

  public static void main(String[] args) {
    List<Comp> comps =
        new ArrayList<>(
            List.of(
                new Comp(5, "HCLI"),
                new Comp(1, "Zycus"),
                new Comp(2, "GreenApple"),
                new Comp(3, "Concentrix"),
                new Comp(4, "Novelvox")));
    comps.stream()
        .sorted(Comparator.comparing(Comp::getName).reversed())
        .collect(Collectors.toList())
        .forEach(s -> System.out.println(s));

    System.out.println("--------------------------");

    comps.stream().sorted().collect(Collectors.toList()).forEach(s -> System.out.println(s));

    System.out.println("--------------------------");

    comps.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList())
        .forEach(s -> System.out.println(s));
  }
}
