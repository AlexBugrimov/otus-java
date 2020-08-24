package ru.otus.junit.runner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.otus.junit.runner.TestClass.Result;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ResultOfRunning {

    private final Class<?> clazz;
    private final List<Result> results;
}
