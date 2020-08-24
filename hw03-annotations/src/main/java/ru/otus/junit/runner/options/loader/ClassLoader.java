package ru.otus.junit.runner.options.loader;

import org.jetbrains.annotations.NotNull;
import ru.otus.junit.runner.options.utils.Mapper;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public final class ClassLoader implements Loader<Class<?>[]> {

    private static final File[] NO_FILES = {};
    private final ClassPath classPath;

    public ClassLoader(@NotNull ClassPath classPath) {
        this.classPath = classPath;
    }

    @Override
    public Class<?>[] load() {
        final File[] files = loadFiles();
        return Arrays.stream(files)
                .map(file -> Mapper.toClass(classPath.getPath(), file))
                .filter(Objects::nonNull)
                .toArray(Class<?>[]::new);
    }

    private File[] loadFiles() {
        final File rootDirectory = classPath.toFile();
        return rootDirectory.isDirectory()
                ? rootDirectory.listFiles(File::isFile)
                : NO_FILES;
    }
}
