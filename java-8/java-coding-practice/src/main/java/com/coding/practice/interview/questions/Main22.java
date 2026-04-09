package com.coding.practice.interview.questions;

import lombok.Builder;
import lombok.Data;

@Data
class Test22 {
  private String name;

  private int age;

  @Builder
  public Test22(String name, int age) {
    this.age = age;
    this.name = name;
  }
}

public class Main22 {
  public static void main(String[] args) {
    System.out.println(Test22.builder().age(22).build());
  }
}
