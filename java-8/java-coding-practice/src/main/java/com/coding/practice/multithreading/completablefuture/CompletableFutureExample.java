package com.coding.practice.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    // Run async task without returning result
    CompletableFuture<Void> future1 =
        CompletableFuture.runAsync(
            () -> {
              System.out.println("Task 1 running in thread: " + Thread.currentThread().getName());
            });

    // Run async task and return result
    CompletableFuture<String> future2 =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println("Task 2 running in thread: " + Thread.currentThread().getName());
              return "Hello";
            });

    // Chaining with thenApply
    CompletableFuture<String> future3 = future2.thenApply(result -> result + " World");

    // Combining two futures
    CompletableFuture<String> combined =
        future3.thenCombine(
            CompletableFuture.supplyAsync(() -> " from CompletableFuture"), (f3, f4) -> f3 + f4);

    // Wait for results
    future1.join(); // Wait for void future
    System.out.println(combined.get());
  }
}
