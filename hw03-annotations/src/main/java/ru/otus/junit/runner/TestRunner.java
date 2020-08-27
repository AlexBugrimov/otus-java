package ru.otus.junit.runner;

import ru.otus.junit.After;
import ru.otus.junit.Before;
import ru.otus.junit.Test;
import ru.otus.junit.runner.options.Options;
import ru.otus.junit.runner.options.loader.Loader;
import ru.otus.junit.runner.options.out.Output;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestRunner implements Runner {

    private final Loader<Class<?>[]> loader;
    private final Output output;
    private final boolean isPrintTestTrace;
    private final boolean isPrintReport;

    public TestRunner(Options options) {
        this.loader = options.getLoader();
        this.output = options.getOutput();
        this.isPrintTestTrace = options.isPrintTestTrace();
        this.isPrintReport = options.isPrintReport();
    }

    @Override
    public void run() {
        Class<?>[] classes = loader.load();
        List<ResultOfRunning> runResults = Stream.of(classes)
                .map(this::invokeTestClass)
                .collect(Collectors.toList());

        if (isPrintTestTrace) {
            this.output.printTestTrace(runResults);
        }

        if (isPrintReport) {
            this.output.printReport(runResults);
        }
    }

    private ResultOfRunning invokeTestClass(Class<?> clazz) {
        var testClass = TestClass.of(clazz);

        testClass.invokeMethods(Before.class);
        final var results = testClass.invokeMethods(Test.class);
        testClass.invokeMethods(After.class);

        return new ResultOfRunning(clazz, results);
    }
}
