package ru.otus.junit.runner;

import ru.otus.junit.After;
import ru.otus.junit.Before;
import ru.otus.junit.Test;
import ru.otus.junit.runner.options.Options;
import ru.otus.junit.runner.options.out.Output;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestRunner implements Runner {

    private final Options options;
    private final Output output;

    public TestRunner(Options options) {
        this.options = options;
        this.output = options.getOutput();
    }

    @Override
    public void run() {
        final Class<?>[] classes = options.getLoader().load();
        for (Class<?> clazz : classes) {
            output.print("Class: " + clazz.getName());
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
                        output.print("\t" + testNumber + ". PASSED: "+ testMethod.getName());
                        passedCount++;
                    } catch (Exception ex) {
                        output.print("\t" + testNumber + ". FAILED: "+ testMethod.getName());
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
