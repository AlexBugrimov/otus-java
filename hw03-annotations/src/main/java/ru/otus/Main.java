package ru.otus;

import ru.otus.junit.loader.ClassLoader;
import ru.otus.junit.logger.ConsoleTestLogger;
import ru.otus.junit.logger.TestLogger;
import ru.otus.junit.runner.Runner;
import ru.otus.junit.runner.TestRunner;

public class Main {

    private static final String PACKAGE_NAME = "ru.otus.tests";

    public static void main(String[] args) {

        final ClassLoader classLoader = new ClassLoader(PACKAGE_NAME);
        final TestLogger testLogger = new ConsoleTestLogger();
        final Runner testRunner = new TestRunner(classLoader, testLogger);
        testRunner.run();
    }
}
