package com.coding.practice.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Ex1 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture<String> future =
        CompletableFuture.supplyAsync(
            () -> {
              Utility.delay(1000);
              return Utility.aTask("Prem");
            });

    System.out.println(future.get());
  }
}
