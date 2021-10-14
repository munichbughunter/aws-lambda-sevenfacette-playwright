package de.aws;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.platform.console.options.CommandLineOptions;
import org.junit.platform.console.options.Details;
import org.junit.platform.console.options.Theme;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class Executor {
    private final CommandLineOptions options = new CommandLineOptions();

    public TestResult runTest(Request input) {
        System.out.println("TESTPACKAGE: " + input.getTestPackage());
        System.out.println("TESTCLASS: " + input.getTestClass());
        System.out.println("TESTMETHOD: " + input.getTestMethod());

        if (!input.getTestMethod().trim().isEmpty()) {
            setTestMethod(input.getTestPackage() + "." + input.getTestClass() + "#" + input.getTestMethod());
        } else if(!input.getTestClass().trim().isEmpty()) {
            setTestClass(input.getTestPackage() + "." + input.getTestClass());
        } else {
            setTestPackage(input.getTestPackage());
        }
        return executeTest();
    }

    private TestResult executeTest() {
        TestResult testResult = new TestResult();
        Optional<TestExecutionSummary> testExecutionSummary = Optional.empty();
        options.setBannerDisabled(false);
        options.setTheme(Theme.UNICODE);
        options.setDetails(Details.TREE);
        Map<String, String> configParams = new HashMap<>();
        configParams.put("FACETTE_CONFIG", "facetteConfig.yml");

        options.setConfigurationParameters(configParams);
        try {
            testExecutionSummary = Optional.ofNullable(new ConsoleTestExecutor(options).execute(new PrintWriter(System.out)));
        } catch (Exception e) {
            testResult.setThrowable(e);
            e.printStackTrace();
        }

        if (testExecutionSummary.isPresent()) {
            testResult.setNumberOfTests(testExecutionSummary.get().getTestsFoundCount());
            testResult.setRunTime(testExecutionSummary.get().getTimeFinished() - testExecutionSummary.get().getTimeStarted());
            testResult.setStartedTests(testExecutionSummary.get().getTestsStartedCount());
            testResult.setSuccessCount(testExecutionSummary.get().getTestsSucceededCount());
            testResult.setIgnoreCount(testExecutionSummary.get().getTestsSkippedCount());
            testResult.setFailureCount(testExecutionSummary.get().getTotalFailureCount());
            testExecutionSummary.get().getFailures().forEach( failure -> testResult.setThrowable(failure.getException()));
        }

        return testResult;
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
}
