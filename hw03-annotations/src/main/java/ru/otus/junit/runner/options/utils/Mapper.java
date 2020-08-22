package ru.otus.junit.runner.options.utils;

import lombok.SneakyThrows;
import ru.otus.junit.runner.TestClass;
import ru.otus.junit.runner.options.out.Color;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Mapper {

    private static final String CLASS_EXTENSION = ".class";
    private static final String EMPTY_STRING = "";
    private static final Predicate<File> IS_CLASS = file -> file.getName().endsWith(CLASS_EXTENSION);

    private Mapper() {
    }

    @SneakyThrows
    public static Class<?> toClass(String path, File file) {
        final String className = fullNameOfClass(path, file);
        return IS_CLASS.test(file)
                ? Class.forName(className)
                : null;
    }

    public static Color toColor(TestClass.Result.Type type) {
        return switch (type) {
            case SUCCESS -> Color.GREEN;
            case ERROR -> Color.RED;
            default -> Color.DEFAULT;
        };
    }

    public static BiFunction<Map<? extends Class<?>, List<TestClass.Result>>, Predicate<TestClass.Result>, Long> toCountResults =
            (results, predicate) -> results.values().stream()
                    .flatMap(List::stream)
                    .filter(predicate)
                    .count();

    private static String fullNameOfClass(String path, File file) {
        return path + '.' + getClassName(file);
    }

    private static String getClassName(File file) {
        final String fileName = file.getName();
        return fileName.replace(CLASS_EXTENSION, EMPTY_STRING);
    }
}
