package ru.otus.junit.runner;

import ru.otus.junit.After;
import ru.otus.junit.Before;
import ru.otus.junit.Test;
import ru.otus.junit.loader.Loader;

import java.lang.annotation.Annotation;
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
            System.out.println("Class: " + clazz.getName());
            final Method[] methods = clazz.getDeclaredMethods();
            int passedCount = 0;
            int failedCount = 0;
            int testNumber = 1;
            try {
                Constructor<?> constructor  = clazz.getConstructor();
                final Method[] testMethods = Arrays.stream(methods)
                        .filter(method -> method.isAnnotationPresent(Test.class)).toArray(Method[]::new);
                for (Method testMethod : testMethods) {
                    final Object instance = constructor.newInstance();
                    findAndInvokeMethod(Before.class, methods, instance);
                    try {
                        testMethod.invoke(instance);
                        System.out.println("\t" + testNumber + ". PASSED: "+ testMethod.getName());
                        passedCount++;
                    } catch (Exception ex) {
                        System.out.println("\t" + testNumber + ". FAILED: "+ testMethod.getName());
                        failedCount++;
                    }
                    findAndInvokeMethod(After.class, methods, instance);
                    testNumber++;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            outReport(passedCount, failedCount);
        }
    }

    private void outReport(int passedCount, int failedCount) {
        System.out.println("\t-----------------------------------");
        System.out.println("\t[PASSED] Успешных тестов: " + passedCount);
        System.out.println("\t[FAILED] Проваленных тестов: " + failedCount);
        System.out.println("\tИТОГО: " + (passedCount + failedCount));
        System.out.println("\t-----------------------------------");
    }

    private void findAndInvokeMethod(Class<? extends Annotation> annotation, Method[] methods, Object instance) {
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(annotation))
                .findFirst().ifPresent(method -> {
            try {
                method.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}
