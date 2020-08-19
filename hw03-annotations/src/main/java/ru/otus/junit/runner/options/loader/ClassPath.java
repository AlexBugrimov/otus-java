package ru.otus.junit.runner.options.loader;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Optional;

@Getter
public class ClassPath {

    private final String path;

    public ClassPath(String path) {
        this.path = path;
    }

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
