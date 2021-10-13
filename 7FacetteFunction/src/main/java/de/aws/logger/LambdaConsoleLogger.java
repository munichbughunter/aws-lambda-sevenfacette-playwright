package de.aws.logger;

import java.util.Arrays;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

/**
 * TODO: Add Description
 *
 */
public class LambdaConsoleLogger implements LambdaLogger {

    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void log(byte[] message) {
        System.out.println(Arrays.toString(message));
    }
}
