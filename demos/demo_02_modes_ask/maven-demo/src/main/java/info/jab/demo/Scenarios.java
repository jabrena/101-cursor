package info.jab.demo;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Scenarios {
    
    private final URI url;

    public Scenarios(URI url) {
        this.url = url;
    }

    /*
     * Scenario 1
     * 
     * Race 2 concurrent requests
     * 
     * GET /1
     * The winner returns a 200 response with a body containing right
     */
    public String scenario1() {
        var client = HttpClient.newHttpClient();
        var targetUrl = url.resolve("/1");

        CompletableFuture<String> request1 = CompletableFuture.supplyAsync(() -> {
            try {
                var request = HttpRequest.newBuilder()
                    .uri(targetUrl)
                    .GET()
                    .build();
                return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
            } catch (Exception e) {
                return "error";
            }
        });

        CompletableFuture<String> request2 = CompletableFuture.supplyAsync(() -> {
            try {
                var request = HttpRequest.newBuilder()
                    .uri(targetUrl)
                    .GET()
                    .build();
                return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
            } catch (Exception e) {
                return "error";
            }
        });

        return CompletableFuture.anyOf(request1, request2)
                .thenApply(result -> (String) result)
                .join();
    }

    List<String> results() {
        return List.of(scenario1());
    }
}
