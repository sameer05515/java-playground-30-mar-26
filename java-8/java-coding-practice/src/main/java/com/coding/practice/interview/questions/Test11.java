package com.coding.practice.interview.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Test11 {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class Product {
    private String name;
    private double price;
    private int quantity;
  }

  public static void main(String[] args) {
    List<Product> product = new ArrayList<>();
    product.add(new Product("radio", 99.0, 0));
    product.add(new Product("television", 82.0, 3));
    product.add(new Product("earbud", 82.5, 5));
    product.add(new Product("charger", 88.5, 0));
    product.add(new Product("mobile", 99.5, 2));

    System.out.println(
        product.stream().map(p -> p.price * p.quantity).collect(Collectors.reducing(Double::sum)));

    double val = 0;
    for (Product p : product) {
      val = val + (p.price * p.quantity);
    }
    System.out.println(val);
  }
}
