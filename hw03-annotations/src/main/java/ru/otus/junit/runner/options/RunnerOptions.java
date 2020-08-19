package ru.otus.junit.runner.options;

import ru.otus.junit.runner.options.loader.Loader;
import ru.otus.junit.runner.options.out.Output;

public class RunnerOptions implements Options {

    private final Loader<Class<?>[]> loader;
    private final Output output;
    private final boolean isShowTestCases;
    private final boolean isShowReport;

    public RunnerOptions(Loader<Class<?>[]> loader,
                         Output output,
                         boolean isShowTestCases,
                         boolean isShowReport) {
        this.loader = loader;
        this.output = output;
        this.isShowTestCases = isShowTestCases;
        this.isShowReport = isShowReport;
    }

    @Override
    public Loader<Class<?>[]> getLoader() {
        return loader;
    }

    @Override
    public Output getOutput() {
        return output;
    }

    @Override
    public boolean isShowTestCases() {
        return isShowTestCases;
    }

    @Override
    public boolean isShowReport() {
        return isShowReport;
    }
}
