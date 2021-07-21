package de.aws;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AppTest {
    @Test
    public void callTestMethod() {
        App app = new App();

        String payload = "{\n" +
                "    \"package\": \"de.aws.api\",\n" +
                "    \"class\": \"DemoTest\",\n" +
                "    \"method\": \"apiDemo\",\n" +
                "    \"browser\": \"chromium\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = app.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));
    }

    @Test
    public void callTestClass() {
        App app = new App();

        String payload = "{\n" +
                "    \"package\": \"de.aws.api\",\n" +
                "    \"class\": \"DemoTest\",\n" +
                "    \"method\": \"\",\n" +
                "    \"browser\": \"chromium\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = app.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));
    }

    @Test
    public void callTestPackage() {
        App app = new App();

        String payload = "{\n" +
                "    \"package\": \"de.aws.ui\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\",\n" +
                "    \"browser\": \"chromium\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = app.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));
    }

    @Test
    public void callAllTestPackage() {
        App app = new App();

        String payload = "{\n" +
                "    \"package\": \"de.aws\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\",\n" +
                "    \"browser\": \"chromium\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = app.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));
    }

    @Test
    public void callChromeBrowser() {
        App app = new App();

        String payload = "{\n" +
                "    \"package\": \"de.aws.ui\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\",\n" +
                "    \"browser\": \"chromium\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = app.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));
    }

    @Test
    public void callConfiguredBrowser() {
        App app = new App();

        String payload = "{\n" +
                "    \"package\": \"de.aws.ui\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\",\n" +
                "    \"browser\": \"webkit\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = app.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));
    }
}
