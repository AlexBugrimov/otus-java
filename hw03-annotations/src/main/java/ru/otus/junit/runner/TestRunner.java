package ru.otus.junit.runner;

import lombok.AllArgsConstructor;
import ru.otus.junit.After;
import ru.otus.junit.Before;
import ru.otus.junit.Test;
import ru.otus.junit.runner.options.Options;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class TestRunner implements Runner {

    private final Options options;

    @Override
    public void run() {
        var classes = options.getLoader().load();
        var runResults = Stream.of(classes)
                .map(this::invokeClass)
                .collect(Collectors.toList());
        System.out.println(runResults);
//        for (Class<?> clazz : classes) {
//            output.print("Class: %s\n", testClass.getName());
//            final Method[] methods = clazz.getDeclaredMethods();
//            int passedCount = 0;
//            int failedCount = 0;
//            int testNumber = 1;
//            testClass.invokeMethods(Before.class);
//            testClass.invokeMethods(Test.class);
//            testClass.invokeMethods(After.class);
//            final Method[] testMethods = Arrays.stream(methods)
//                    .filter(method -> method.isAnnotationPresent(Test.class)).toArray(Method[]::new);
//            for (Method testMethod : testMethods) {
//                final Object instance = testClass.instance();
//                findAndInvokeMethod(Before.class, methods, instance);
//                try {
//                    testMethod.invoke(instance);
//                    output.print("\t %s. PASSED: %s\n", testNumber, testMethod.getName());
//                    passedCount++;
//                } catch (Exception ex) {
//                    output.print("\t %s. FAILED: %s\n", testNumber, testMethod.getName());
//                    failedCount++;
//                }
//                findAndInvokeMethod(After.class, methods, instance);
//                testNumber++;
//            }
//            outReport(passedCount, failedCount);
//        }
    }

    private Map<Class<?>, List<TestClass.Result>> invokeClass(Class<?> clazz) {
        var testClass = TestClass.of(clazz);

        testClass.invokeMethods(Before.class);
        final var results = testClass.invokeMethods(Test.class);
        testClass.invokeMethods(After.class);

        return Map.of(clazz, results);
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
