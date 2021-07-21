package de.aws;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonInclude;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    @JsonProperty("package")
    private String testPackage;

    @JsonProperty("class")
    private String testClass;

    @JsonProperty("method")
    private String testMethod;

    @JsonProperty("browser")
    private String browser;

    public String getTestPackage() {
        return testPackage;
    }

    public void setTestPackage(String testPackage) {
        this.testPackage = testPackage;
    }

    public String getTestClass() {
        return testClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

    public String getTestMethod() {
        return testMethod;
    }

    public void setTestMethod(String testMethod) {
        this.testMethod = testMethod;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
