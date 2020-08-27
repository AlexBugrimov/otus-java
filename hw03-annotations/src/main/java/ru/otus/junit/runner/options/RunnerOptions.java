package ru.otus.junit.runner.options;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.otus.junit.runner.options.loader.Loader;
import ru.otus.junit.runner.options.out.Output;

@Getter
@RequiredArgsConstructor
public class RunnerOptions implements Options {

    @NonNull
    private final Loader<Class<?>[]> loader;
    @NonNull
    private final Output output;
    private final boolean isPrintTestTrace;
    private final boolean isPrintReport;
}
