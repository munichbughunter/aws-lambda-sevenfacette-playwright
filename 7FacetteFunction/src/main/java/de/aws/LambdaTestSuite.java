package de.aws;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add Description
 *
 */
public class LambdaTestSuite {

    private final List<String> testSuite = new ArrayList<>();

    public void addTest(String testsToExecute) {
        testSuite.add(testsToExecute);
    }

    @Override
    public String toString() {
        return String.format("{ \"Analyzing-Date\": \"%s\", \"Detail\": { \"TestCaseCount\": \"%d\", \"TestSuite\": [" + String.join(",", testSuite) + "]}}", LocalDateTime.now(), testSuite.size());
    }
}
