package de.aws;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LambdaTestHandlerTest {

    @Test
    public void extractString() {
        String data = " {body={\"package\": \"de.aws.api\", \"class\": \"\", \"method\": \"\"}, headers={Accept=*/*, Content-Length=52, Content-Type=application/x-www-form-urlencoded, Host=127.0.0.1:3000, User-Agent=curl/7.64.1, X-Forwarded-Port=3000, X-Forwarded-Proto=http}, httpMethod=POST, isBase64Encoded=false, multiValueHeaders={Accept=[*/*], Content-Length=[52], Content-Type=[application/x-www-form-urlencoded], Host=[127.0.0.1:3000], User-Agent=[curl/7.64.1], X-Forwarded-Port=[3000], X-Forwarded-Proto=[http]}, multiValueQueryStringParameters=null, path=/test, pathParameters=null, queryStringParameters=null, requestContext={accountId=123456789012, apiId=1234567890, domainName=127.0.0.1:3000, extendedRequestId=null, httpMethod=POST, identity={accountId=null, apiKey=null, caller=null, cognitoAuthenticationProvider=null, cognitoAuthenticationType=null, cognitoIdentityPoolId=null, sourceIp=127.0.0.1, user=null, userAgent=Custom User Agent String, userArn=null}, path=/test, protocol=HTTP/1.1, requestId=117452ca-9526-495b-a429-07e06faabec3, requestTime=13/Oct/2021:13:08:59 +0000, requestTimeEpoch=1634130539, resourceId=123456, resourcePath=/test, stage=Prod}, resource=/test, stageVariables=null, version=1.0}";
        //str.indexOf("is", str.indexOf("is") + 1);
        data.indexOf("{", data.indexOf("{") + 1);
        String substring = data.substring(data.indexOf("{", data.indexOf("{") + 1), data.indexOf("}") + 1);
        System.out.println(substring);

        RequestMapper requestMapper = new RequestMapper();
        var request = requestMapper.mapJsonToObject(substring, Request.class);
        System.out.println("Und");
    }

    @Test
    public void testList() {
        Request requestOne = new Request();
        requestOne.setTestPackage("package.one");
        requestOne.setTestClass("class.one");
        requestOne.setTestMethod("method.one");

        Request requestTwo = new Request();
        requestTwo.setTestPackage("package.two");
        requestTwo.setTestClass("class.two");
        requestTwo.setTestMethod("method.two");

        Request requestThree = new Request();
        requestThree.setTestPackage("package.three");
        requestThree.setTestClass("class.three");
        requestThree.setTestMethod("method.three");

        List<Request> requestList = new ArrayList<>();
        requestList.add(requestOne);
        requestList.add(requestTwo);
        requestList.add(requestThree);

        Response response = new Response();
        response.setTestsuite(requestList);


    }
    /*
    @Test
    public void callWithTestMethod() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.api\",\n" +
                "    \"class\": \"DemoTest\",\n" +
                "    \"method\": \"apiDemo\"\n" +
                "}";

        Request inputRequest = new Request();
        inputRequest.setTestPackage("de.aws.api");
        inputRequest.setTestClass("DemoTest");
        inputRequest.setTestMethod("apiDemo");


        LambdaResponse result = lambdaTestHandler.handleRequest(inputRequest, null);
        //assertEquals(200, result.getStatusCode().intValue());
        //assertEquals("application/json", result.getHeaders().get("Content-Type"));
        System.out.println(result.getResponse());

        //assertNotNull(content);
        //assertTrue(content.contains("\"numberOfTests\":1"));
        //assertTrue(content.contains("\"startedTests\":1"));
        //assertTrue(content.contains("\"successCount\":1"));
        //assertTrue(content.contains("\"failureCount\":0"));
    }

    /*
    @Test
    public void callWithTestPackage() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.api\",\n" +
                "    \"class\": \"\",\n" +
                "    \"method\": \"\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(200, result.getStatusCode().intValue());
        assertEquals("application/json", result.getHeaders().get("Content-Type"));
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"Analyzing-Date\""));
        assertTrue(content.contains("\"Detail\":"));
        assertTrue(content.contains("\"TestSuite\":"));
        assertTrue(content.contains("\"package\":\"de.aws.api\""));
        assertTrue(content.contains("\"class\":\"DemoTest\""));
        assertTrue(content.contains("\"method\":\"apiDemoTwo\""));
        assertTrue(content.contains("\"method\":\"apiDemo\""));
    }

    @Test
    public void callUITestMethod() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.ui\",\n" +
                "    \"class\": \"UIDemoTest\",\n" +
                "    \"method\": \"playwrightChromeDemo\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(200, result.getStatusCode().intValue());
        assertEquals("application/json", result.getHeaders().get("Content-Type"));
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"numberOfTests\":1"));
        assertTrue(content.contains("\"startedTests\":1"));
        assertTrue(content.contains("\"successCount\":1"));
        assertTrue(content.contains("\"failureCount\":0"));
    }

    @Test
    public void callAllUITestMethod() {
        LambdaTestHandler lambdaTestHandler = new LambdaTestHandler();

        String payload = "{\n" +
                "    \"package\": \"de.aws.ui\",\n" +
                "    \"class\": \"UIDemoTest\",\n" +
                "    \"method\": \"\"\n" +
                "}";

        APIGatewayProxyRequestEvent inputEvent = new APIGatewayProxyRequestEvent();
        inputEvent.setBody(payload);
        APIGatewayProxyResponseEvent result = lambdaTestHandler.handleRequest(inputEvent, null);
        assertEquals(200, result.getStatusCode().intValue());
        assertEquals("application/json", result.getHeaders().get("Content-Type"));
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"Analyzing-Date\""));
        assertTrue(content.contains("\"Detail\":"));
        assertTrue(content.contains("\"TestSuite\":"));
        assertTrue(content.contains("\"package\":\"de.aws.ui\""));
        assertTrue(content.contains("\"class\":\"UIDemoTest\""));
        assertTrue(content.contains("\"method\":\"playwrightChromeDemo\""));
        assertTrue(content.contains("\"method\":\"playwrightDemo\""));
    }

     */
}
