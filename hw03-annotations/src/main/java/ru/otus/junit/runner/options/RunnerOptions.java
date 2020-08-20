package ru.otus.junit.runner.options;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.otus.junit.runner.options.loader.Loader;
import ru.otus.junit.runner.options.out.Output;

@Getter
@RequiredArgsConstructor
public class RunnerOptions implements Options {

    private final Loader<Class<?>[]> loader;
    private final Output output;
    private final boolean isShowTestCases;
    private final boolean isShowReport;
}
