package de.aws.logger;

/**
 * TODO: Add Description
 */

public class LoggerContainer {
    public static Logger LOGGER = new Logger(new LambdaConsoleLogger());
}
