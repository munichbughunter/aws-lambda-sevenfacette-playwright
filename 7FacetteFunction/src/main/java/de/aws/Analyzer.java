package de.aws;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

/**
 * Explores the tests and calls the Lambda Function to execute
 */
public class Analyzer {
    public static final String TEST = ".*Test*";
    private final Request request;

    public Analyzer(Request request) {
        this.request = request;
    }

    private LauncherDiscoveryRequest filterWithPackage() {
        return LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage(request.getTestPackage())
                )
                .filters(
                        includeClassNamePatterns(TEST)
                )
                .build();
    }

    private LauncherDiscoveryRequest filterWithClass() {
        return LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage(request.getTestPackage()),
                        selectClass(request.getTestPackage() + "." + request.getTestClass())
                )
                .filters(
                        includeClassNamePatterns(TEST)
                )
                .build();
    }

    public List<Request> exploreTests() {
        if (!request.getTestMethod().trim().isEmpty()) {
            List<Request> identifiedTests = new ArrayList<>();
            Request testRequest = new Request();
            testRequest.setTestPackage(request.getTestPackage());
            testRequest.setTestClass(request.getTestClass());
            testRequest.setTestMethod(request.getTestMethod());
            identifiedTests.add(testRequest);
            System.out.println("Identified TEST: " + identifiedTests.get(0));
            return identifiedTests;
        }
        else if (!request.getTestClass().trim().isEmpty()) {
            System.out.println("Searching by CLASS");
            return retrieveTestList(filterWithClass());
        } else {
            System.out.println("Searching by PACKAGE");
            return retrieveTestList(filterWithPackage());
        }
    }

    private List<Request> retrieveTestList(LauncherDiscoveryRequest launcherDiscoveryRequest) {
        List<Request> identifiedTests = new ArrayList<>();

        try {
            LauncherSession launcherSession = LauncherFactory.openSession();
            Launcher launcher = launcherSession.getLauncher();
            // Discover tests and build a test plan
            TestPlan testPlan = launcher.discover(launcherDiscoveryRequest);
            // Get the root TestIdentifiers for this test plan.
            // A TestIdentifier is an immutable data transfer object that represents a test or container which is usually part of a TestPlan.
            TestIdentifier rootTestContainer = testPlan.getRoots().iterator().next();
            // Get the children of the supplied TestIdentifier - in this case an object that represents a container for each class
            Set<TestIdentifier> testContainer = testPlan.getChildren(rootTestContainer);
            // Iterate over the TestContainer
            testContainer.forEach(testIdentifier -> {
                // Extract the package structure like de.aws.api.DemoTest
                String packageClassName = testIdentifier.getLegacyReportingName();

                String packageName = packageClassName.substring(0, packageClassName.lastIndexOf('.'));
                String testClass = testIdentifier.getDisplayName();
                // Get the tests
                Set<TestIdentifier> tests = testPlan.getChildren(testIdentifier);
                // Iterate over the identified tests and extract the method name from a test class
                tests.forEach(testMethod -> {
                    Request testRequest = new Request();
                    testRequest.setTestPackage(packageName);
                    testRequest.setTestClass(testClass);
                    testRequest.setTestMethod(testMethod.getDisplayName().substring(0, testMethod.getDisplayName().lastIndexOf('(')));

                    identifiedTests.add(testRequest);
                });
            });

        } catch (Exception e) {
            System.out.println("HAHAHAHAHAHHAHAHAHA");
        }

        /*try (LauncherSession launcherSession = LauncherFactory.openSession()) {
            Launcher launcher = launcherSession.getLauncher();
            // Discover tests and build a test plan
            TestPlan testPlan = launcher.discover(launcherDiscoveryRequest);
            // Get the root TestIdentifiers for this test plan.
            // A TestIdentifier is an immutable data transfer object that represents a test or container which is usually part of a TestPlan.
            TestIdentifier rootTestContainer = testPlan.getRoots().iterator().next();
            // Get the children of the supplied TestIdentifier - in this case an object that represents a container for each class
            Set<TestIdentifier> testContainer = testPlan.getChildren(rootTestContainer);
            // Iterate over the TestContainer
            testContainer.forEach( testIdentifier -> {
                // Extract the package structure like de.aws.api.DemoTest
                String packageClassName = testIdentifier.getLegacyReportingName();
                String packageName = packageClassName.substring(0, packageClassName.lastIndexOf('.'));
                String testClass = testIdentifier.getDisplayName();
                // Get the tests
                Set<TestIdentifier> tests = testPlan.getChildren(testIdentifier);
                // Iterate over the identified tests and extract the method name from a test class
                tests.forEach(testMethod -> {
                    Request testRequest = new Request();
                    testRequest.setTestPackage(packageName);
                    testRequest.setTestClass(testClass);
                    testRequest.setTestMethod(testMethod.getDisplayName().substring(0, testMethod.getDisplayName().lastIndexOf('(')));
                    identifiedTests.add(testRequest);
                });
            });
            return identifiedTests;
        }*/
        return identifiedTests;
    }
}
