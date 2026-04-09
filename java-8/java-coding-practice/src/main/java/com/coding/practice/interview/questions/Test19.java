package com.coding.practice.interview.questions;

public class Test19 {
  static int x = 0;

  static {
    x = x++ + ++x + x-- + --x;
  }

  {
    x = x++ + ++x;
  }

  public static void main(String[] args) {
    new Test19();
    System.out.println(x);
  }
}
