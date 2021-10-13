package de.aws;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RequestHandlerTest {

    @Test
    public void callTestMethod() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.api\",\n" +
                "    \"class\": \"DemoTest\",\n" +
                "    \"method\": \"apiDemo\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
        System.out.println(content);
        /*assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));*/
    }

    @Test
    public void callTestPlan() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        System.out.println(content);
        assertNotNull(content);
        assertTrue(content.contains("\"Analyzing-Date\""));
        assertTrue(content.contains("\"TestCaseCount\": \"3\""));
        assertTrue(content.contains("\"TestSuite\""));
        assertTrue(content.contains("\"package\":\"de.aws.api\""));
        assertTrue(content.contains("\"class\":\"DemoTest\""));
        assertTrue(content.contains("\"method\":\"apiDemo\""));
    }

    @Test
    public void callTestClass() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.ui\",\n" +
                "    \"class\": \"UIDemoTest\",\n" +
                "    \"method\": \"\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        System.out.println(content);
        /*assertNotNull(content);
        assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));*/
    }

    @Test
    public void callTestPackage() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.api\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        /*assertNotNull(content);
        assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));*/
    }

    @Test
    public void callAllTestPackage() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.api\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        /*assertNotNull(content);
        assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));*/
    }

    @Test
    public void callChromeBrowser() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.api\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
        /*assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));*/
    }

    @Test
    public void callConfiguredBrowser() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.api\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(result.getStatusCode().intValue(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
        /*assertTrue(content.contains("\"message\""));
        assertTrue(content.contains("\"Test triggered\""));
        assertTrue(content.contains("\"location\""));*/
    }
}
