package com.coding.practice.httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class AsyncPostJsonExample {
  public static void main(String[] args) {
    HttpClient client = HttpClient.newHttpClient();

    String jsonBody =
        """
            {
              "name": "John Doe",
              "age": 30
            }
            """;

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
            .timeout(Duration.ofSeconds(10))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(jsonBody))
            .build();

    CompletableFuture<HttpResponse<String>> futureResponse =
        client.sendAsync(request, BodyHandlers.ofString());

    futureResponse
        .thenApply(HttpResponse::body)
        .thenAccept(body -> System.out.println("Response: " + body))
        .exceptionally(
            e -> {
              System.err.println("Error: " + e.getMessage());
              return null;
            });

    System.out.println("Request sent asynchronously... main thread is free!");

    // Keep main alive until async completes
    futureResponse.join();
  }
}
