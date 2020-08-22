package ru.otus.junit.runner.options.out;

import ru.otus.junit.runner.TestClass;

import java.util.List;
import java.util.Map;

public interface Output {

    void printTestTrace(Map<? extends Class<?>, List<TestClass.Result>> results);

    void printReport(Map<? extends Class<?>, List<TestClass.Result>> results);
}
