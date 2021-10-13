package de.aws;

import static java.util.Optional.ofNullable;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonIgnore;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonInclude;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

/**
 * TODO: Add Description
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestResult {

    @JsonProperty("throwable")
    private Throwable throwable;

    @JsonProperty("numberOfTests")
    private long numberOfTests;

    @JsonProperty("startedTests")
    private long startedTests;

    @JsonProperty("successCount")
    private long successCount;

    @JsonProperty("failureCount")
    private long failureCount;

    @JsonProperty("ignoreCount")
    private long ignoreCount;

    @JsonProperty("runTime")
    private long runTime;

    public TestResult(TestExecutionSummary testExecutionSummary) {
        numberOfTests = testExecutionSummary.getTestsFoundCount();
        successCount = testExecutionSummary.getTestsSucceededCount();
        startedTests = testExecutionSummary.getTestsStartedCount();
        failureCount = testExecutionSummary.getTotalFailureCount();
        //failureCount = testExecutionSummary.getTestsFailedCount();
        ignoreCount = testExecutionSummary.getTestsSkippedCount();

        if (!wasSuccessful()) {
            throwable = testExecutionSummary.getFailures().get(0).getException();
        }
    }

    public TestResult() {
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public long getNumberOfTests() {
        return numberOfTests;
    }

    public void setNumberOfTests(long numberOfTests) {
        this.numberOfTests = numberOfTests;
    }

    public long getStartedTests() {
        return startedTests;
    }

    public void setStartedTests(long startedTests) {
        this.startedTests = startedTests;
    }

    public long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(long successCount) {
        this.successCount = successCount;
    }

    public long getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(long failureCount) {
        this.failureCount = failureCount;
    }

    public long getIgnoreCount() {
        return ignoreCount;
    }

    public void setIgnoreCount(long ignoreCount) {
        this.ignoreCount = ignoreCount;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    @JsonIgnore
    public boolean wasSuccessful() {
        return ofNullable(throwable).isEmpty();
    }
}
