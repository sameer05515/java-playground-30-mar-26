package com.coding.practice.interview.questions;

public abstract class Test20 {

  public abstract int sum(int a, int b);

  public static void main(String[] args) {
    System.out.println("I am running from an abstract class: " + new Impl().sum(4, 4));
  }

  static class Impl extends Test20 {
    @Override
    public int sum(int a, int b) {
      return a + b;
    }
  }
}
