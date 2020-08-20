package ru.otus.junit.runner;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(staticName = "of")
public class TestClass {

    @NonNull private final Class<?> clazz;
    private Object instance;

    @SneakyThrows
    public Object instance(Object... args) {
        if (instance == null) {
            instance = getConstructor().newInstance(args);
        }
        return instance;
    }

    public List<Result> invokeMethods(Class<? extends Annotation> annotation) {
        final Method[] methods = clazz.getDeclaredMethods();
        return Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(annotation))
                .map(method -> {
                    try {
                        method.invoke(instance());
                        return new Result(
                                Result.Type.SUCCESS,
                                method.getName());
                    } catch (InvocationTargetException ex) {
                        return new Result(
                                Result.Type.ERROR,
                                String.format("%s, error: %s", method.getName(), ex.getTargetException().getMessage()));
                    } catch (Exception ex) {
                        return new Result(Result.Type.ERROR, ex.getMessage());
                    }
                }).collect(Collectors.toList());
    }

    @Getter
    public static class Result {

        final Type type;
        final String message;

        public Result(Type type, String message) {
            this.type = type;
            this.message = message;
        }

        enum Type {
            SUCCESS, ERROR
        }
    }

    @NotNull
    private Constructor<?> getConstructor() throws NoSuchMethodException {
        return clazz.getConstructor();
    }
}
