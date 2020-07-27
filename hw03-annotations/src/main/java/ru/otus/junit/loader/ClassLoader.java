package ru.otus.junit.loader;

import java.io.File;
import java.util.Arrays;

import static ru.otus.junit.loader.ClassLoaderUtils.getResource;
import static ru.otus.junit.loader.ClassLoaderUtils.toClassName;

public final class ClassLoader implements Loader<Class<?>[]> {

    private final String packageName;

    public ClassLoader(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public Class<?>[] load() {
        final File[] files = loadFiles();
        return Arrays.stream(files).map(this::toClass).toArray(Class<?>[]::new);
    }

    private Class<?> toClass(File file) {
        try {
            if (file.getName().endsWith(".class")) {
                return Class.forName(toClassName(packageName, file));
            }
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found", e);
        }
    }

    private File[] loadFiles() {
        final File rootDirectory = new File(getResource(packageName));
        if (rootDirectory.isDirectory()) {
            return rootDirectory.listFiles(File::isFile);
        }
        return new File[0];
    }
}
