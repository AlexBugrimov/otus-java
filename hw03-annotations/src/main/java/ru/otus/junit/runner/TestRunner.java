package ru.otus.junit.runner;

import ru.otus.junit.loader.Loader;

public class TestRunner implements Runner {

    private final Loader<Class<?>[]> classLoader;

    public TestRunner(Loader<Class<?>[]> classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void run() {
        final Class<?>[] classes = classLoader.load();
    }
}
