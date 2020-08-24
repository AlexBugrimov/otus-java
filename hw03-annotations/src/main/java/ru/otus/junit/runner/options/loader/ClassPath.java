package ru.otus.junit.runner.options.loader;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Optional;

@Data
public class ClassPath {

    private final String path;

    public File toFile() {
        return new File(getPathName());
    }

    @NotNull
    private String getPathName() {
        final var unixPath = getUnixPath(path);
        final var loader = ClassPath.class.getClassLoader();
        final var url = Optional.ofNullable(loader.getResource(unixPath));
        return url.orElseThrow().getFile();
    }

    @NotNull
    private String getUnixPath(String path) {
        return path.replace('.', '/');
    }
}
