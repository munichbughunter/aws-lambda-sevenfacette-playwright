package de.aws;

import java.util.List;

import com.amazonaws.lambda.thirdparty.com.google.gson.Gson;
import com.amazonaws.lambda.thirdparty.com.google.gson.GsonBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Handler for requests to Lambda function.
 */
public class LambdaTestHandler implements RequestHandler<Object, Response> {

    public Response handleRequest(final Object input, final Context context) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonData = gson.toJson(input);

        RequestMapper requestMapper = new RequestMapper();
        var request = requestMapper.mapJsonToObject(jsonData, Request.class);

        Analyzer analyzer = new Analyzer(request);
        var testsToExecute = analyzer.exploreTests();

        if (testsToExecute.size() == 1) {
            Executor executor = new Executor();
            TestResult testResult = executor.runTest(testsToExecute.get(0));
            return createResponse(testResult);
        } else {
            LambdaTestSuite testSuite = new LambdaTestSuite();
            return createResponse(testsToExecute);
        }
    }

    private Response createResponse(TestResult testResult) {
        Response response = new Response();
        response.setFailureCount(testResult.getFailureCount());
        response.setIgnoreCount(testResult.getIgnoreCount());
        response.setNumberOfTests(testResult.getNumberOfTests());
        response.setRunTime(testResult.getRunTime());
        response.setStartedTests(testResult.getStartedTests());
        response.setThrowable(testResult.getThrowable());
        response.setSuccessCount(testResult.getSuccessCount());

        return response;
    }

    private Response createResponse(List<Request> testSuite) {
        Response response = new Response();
        response.setTestsuite(testSuite);
        return response;
    }
}
