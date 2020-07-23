package ru.otus.junit;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public final class ClassLoader implements Loader {

    private static final String JAVA_FILE = "java";
    private final String rootDir;

    public ClassLoader(String path) {

        this.rootDir = getClass().getClassLoader().getResource(path).getFile();
    }

    @Override
    public Class<?>[] getClasses() {
        final File[] files = loadFiles();
        return Arrays.stream(files).map(this::toClass).toArray(Class<?>[]::new);
    }

    private Class<?> toClass(File file) {
        final String className = file.getAbsolutePath();
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(String.format("Class '%s' not found", className), e);
        }
    }

    private File[] loadFiles() {
        File file = new File(rootDir);
        if (file.isDirectory()) {
            return file.listFiles(pathname -> pathname.isFile());
        }
        return new File[0];
    }

    private boolean isJavaFile(File file) {
        return file.toPath().endsWith(JAVA_FILE);
    }
}
