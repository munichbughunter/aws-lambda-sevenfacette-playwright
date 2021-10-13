package de.aws;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Invoker {

    public void callForTest(String testRequest) {
        HttpClient httpClient = HttpClient.newHttpClient();
        httpClient.sendAsync(buildRequest(testRequest), HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> { System.out.println(response.statusCode());
                    return response; } )
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }

    private HttpRequest buildRequest(final String request) {
        return HttpRequest.newBuilder()
                .uri(URI.create(System.getenv("ENDPOINT")))
                .timeout(Duration.ofMinutes(15))
                .POST(HttpRequest.BodyPublishers.ofString(request))
                .build();
    }
}
