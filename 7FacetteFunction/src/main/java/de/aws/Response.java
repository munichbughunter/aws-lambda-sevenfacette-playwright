package de.aws;

import java.util.List;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonInclude;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO: Add Description
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

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

    @JsonProperty("testsuite")
    private List<Request> testsuite = null;

    public List<Request> getTestsuite() {
        return testsuite;
    }

    public void setTestsuite(List<Request> testsuite) {
        this.testsuite = testsuite;
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
}
