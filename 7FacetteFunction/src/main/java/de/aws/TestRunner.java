package de.aws;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.platform.console.options.CommandLineOptions;
import org.junit.platform.console.options.Details;
import org.junit.platform.console.options.Theme;
import org.junit.platform.console.tasks.ConsoleTestExecutor;

public class TestRunner {
    private final CommandLineOptions options = new CommandLineOptions();

    public void runTest(String input) {
        var request = mapJsonToObject(input, Request.class);

        if (!request.getTestMethod().trim().isEmpty()) {
            setTestMethod(request.getTestPackage() + "." + request.getTestClass() + "#" + request.getTestMethod());
        } else if(!request.getTestClass().trim().isEmpty()) {
            setTestClass(request.getTestPackage() + "." + request.getTestClass());
        } else {
            setTestPackage(request.getTestPackage());
        }
        executeTest();
    }

    private void executeTest() {
        options.setBannerDisabled(false);
        options.setTheme(Theme.UNICODE);
        options.setDetails(Details.TREE);
        Map<String, String> configParams = new HashMap<>();
        configParams.put("FACETTE_CONFIG", "facetteConfig.yml");
        options.setConfigurationParameters(configParams);
        try {
            new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTestMethod(String testMethodString) {
        this.options.setSelectedMethods(Collections.singletonList(testMethodString));
    }

    private void setTestClass(String testMethodString) {
        this.options.setSelectedClasses(Collections.singletonList(testMethodString));
    }

    private void setTestPackage(String testMethodString) {
        this.options.setSelectedPackages(Collections.singletonList(testMethodString));
    }

    private <T> T mapJsonToObject(final String requestBody, final Class<T> clazz) {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return objectMapper.readValue(requestBody, clazz);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
