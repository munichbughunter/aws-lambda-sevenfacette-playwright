package de.aws;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.SerializationFeature;

public class RequestMapper {

    public <T> T mapJsonToObject(final String requestBody, final Class<T> clazz) {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return objectMapper.readValue(requestBody, clazz);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getValueAsString(final Request request) {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return objectMapper.writeValueAsString(request);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getResultAsString(final TestResult testResult) {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return objectMapper.writeValueAsString(testResult);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
