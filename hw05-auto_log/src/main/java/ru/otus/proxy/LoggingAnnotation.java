package ru.otus.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class LoggingAnnotation {

    private LoggingAnnotation() {
    }

    public static boolean isPresentAnnotation(Object obj,
                                              Method method,
                                              Class<? extends Annotation> annotation) throws NoSuchMethodException {
        return obj.getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .isAnnotationPresent(annotation);
    }

    public static void log(String template, Object... args) {
        System.out.printf(template, args);
    }
}
