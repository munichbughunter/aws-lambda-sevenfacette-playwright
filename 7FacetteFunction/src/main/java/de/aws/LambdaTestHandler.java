package de.aws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * Handler for requests to Lambda function.
 */
public class LambdaTestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        RequestMapper requestMapper = new RequestMapper();
        var request = requestMapper.mapJsonToObject(input.getBody(), Request.class);

        Analyzer analyzer = new Analyzer(request);
        var testsToExecute = analyzer.exploreTests();

        // ToDo: Warum direkt ausführen und nicht erst über die TestSuite gehen?
        if (testsToExecute.size() == 1) {
            Executor executor = new Executor();
            TestResult testResult = executor.runTest(testsToExecute.get(0));
            System.out.println(requestMapper.getResultAsString(testResult));
            return createTestSuiteResponse(requestMapper.getResultAsString(testResult));
        } else {
            LambdaTestSuite testSuite = new LambdaTestSuite();

            testsToExecute.forEach(test -> testSuite.addTest(requestMapper.getValueAsString(test)));
            return createTestSuiteResponse(testSuite.toString());
        }
    }

    private APIGatewayProxyResponseEvent createTestSuiteResponse(String testSuite) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return new APIGatewayProxyResponseEvent()
                .withHeaders(headers)
                .withStatusCode(200)
                .withBody(testSuite);

    }
}
