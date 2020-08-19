package ru.otus.junit.runner.options;

import ru.otus.junit.runner.options.loader.Loader;
import ru.otus.junit.runner.options.out.Output;

public interface Options {

    Loader<Class<?>[]> getLoader();

    Output getOutput();

    boolean isShowTestCases();

    boolean isShowReport();
}
