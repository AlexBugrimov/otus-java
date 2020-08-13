package ru.otus.junit.runner;

import ru.otus.junit.After;
import ru.otus.junit.Before;
import ru.otus.junit.Test;
import ru.otus.junit.loader.Loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestRunner implements Runner {

    private final Loader<Class<?>[]> classLoader;

    public TestRunner(Loader<Class<?>[]> classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void run() {
        final Class<?>[] classes = classLoader.load();

        for (Class<?> clazz : classes) {
            final Method[] methods = getMethods(clazz);
            int passedCount = 0;
            int failedCount = 0;
            int testNumber = 1;
            try {
                Constructor<?> constructor  = clazz.getConstructor();
                final Method[] testMethods = Arrays.stream(methods)
                        .filter(method -> method.isAnnotationPresent(Test.class)).toArray(Method[]::new);
                for (Method testMethod : testMethods) {
                    final Object instance = constructor.newInstance();
                    Arrays.stream(methods)
                            .filter(method -> method.isAnnotationPresent(Before.class))
                            .findFirst().ifPresent(method -> {
                        try {
                            method.invoke(instance);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
                    try {
                        testMethod.invoke(instance);

                        System.out.println(testNumber + ". PASSED: "+ testMethod.getName());
                        passedCount++;
                    } catch (Exception ex) {
                        System.out.println(testNumber + ". FAILED: "+ testMethod.getName());
                        failedCount++;
                    }
                    Arrays.stream(methods)
                            .filter(method -> method.isAnnotationPresent(After.class))
                            .findFirst().ifPresent(method -> {
                        try {
                            method.invoke(instance);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
                    testNumber++;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println("---------------------------");
            System.out.println("[PASSED] Успешных тестов: " + passedCount);
            System.out.println("[FAILED] Проваленных тестов: " + failedCount);
            System.out.println("---------------------------");
            System.out.println("ИТОГО тестов: " + (passedCount + failedCount));
        }
    }

    private Method[] getMethods(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }
}
