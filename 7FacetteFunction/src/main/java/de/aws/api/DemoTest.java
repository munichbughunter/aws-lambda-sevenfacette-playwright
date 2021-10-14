package de.aws.api;

import java.util.Objects;

import de.p7s1.qa.sevenfacette.http.GenericHttpClient;
import de.p7s1.qa.sevenfacette.http.HttpClientFactory;
import de.p7s1.qa.sevenfacette.http.HttpHeader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DemoTest {

    @Test
    void apiDemo() {
        final GenericHttpClient apiHttpClient = HttpClientFactory.createClient("apiClient");
        var httpResponse = apiHttpClient.get("/us/90210", new HttpHeader());
        System.out.println(httpResponse.getStatus());
        Assertions.assertEquals(200, Objects.requireNonNull(httpResponse).getStatus());
    }

    @Test
    void apiDemoTwo() {
        final GenericHttpClient apiHttpClient = HttpClientFactory.createClient("apiClient");
        var httpResponse = apiHttpClient.get("/us/90210", new HttpHeader());
        System.out.println(httpResponse.getStatus());
        Assertions.assertEquals(200, Objects.requireNonNull(httpResponse).getStatus());
    }
}
