package ru.otus.junit.loader;

import java.io.File;
import java.util.Objects;

public class ClassLoaderUtils {

    public static String getResource(String packageName) {
        return Objects.requireNonNull(
                ClassLoaderUtils.class
                        .getClassLoader()
                        .getResource(toPath(packageName))).getFile();
    }

    public static String toClassName(String packageName, File file) {
        return packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
    }

    private static String toPath(String packageName) {
        return packageName.replace('.', '/');
    }
}
