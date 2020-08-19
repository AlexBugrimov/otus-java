package ru.otus;

import ru.otus.junit.runner.TestRunner;
import ru.otus.junit.runner.options.Options;
import ru.otus.junit.runner.options.RunnerOptions;
import ru.otus.junit.runner.options.loader.ClassLoader;
import ru.otus.junit.runner.options.loader.ClassPath;
import ru.otus.junit.runner.options.out.ConsoleOutput;

public class Main {

    public static void main(String[] args) {

        final Options options = new RunnerOptions(
                new ClassLoader(new ClassPath("ru.otus.tests")),
                new ConsoleOutput(),
                true,
                true
        );
        new TestRunner(options)
                .run();
    }
}
