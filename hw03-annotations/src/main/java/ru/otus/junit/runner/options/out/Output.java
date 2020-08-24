package ru.otus.junit.runner.options.out;

import ru.otus.junit.runner.ResultOfRunning;
import ru.otus.junit.runner.TestClass;

import java.util.List;
import java.util.Map;

public interface Output {

    void printTestTrace(List<ResultOfRunning> results);

    void printReport(List<ResultOfRunning> results);
}
