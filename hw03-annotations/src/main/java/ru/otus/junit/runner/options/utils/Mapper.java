package ru.otus.junit.runner.options.utils;

import lombok.SneakyThrows;

import java.io.File;
import java.util.function.Predicate;

public class Mapper {

    private static final String CLASS_EXTENSION = ".class";
    private static final String EMPTY_STRING = "";
    private static final Predicate<File> IS_CLASS = file -> file.getName().endsWith(CLASS_EXTENSION);

    private Mapper() {}

    @SneakyThrows
    public static Class<?> toClass(String path, File file) {
        final String className = fullNameOfClass(path, file);
        return IS_CLASS.test(file)
                ? Class.forName(className)
                : null;
    }

    private static String fullNameOfClass(String path, File file) {
        return path + '.' + getClassName(file);
    }

    private static String getClassName(File file) {
        final String fileName = file.getName();
        return fileName.replace(CLASS_EXTENSION, EMPTY_STRING);
    }
}
