package ru.otus.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class LoggingAnnotation {

    private LoggingAnnotation() {}

    public static boolean isPresentAnnotation(Object obj, Method method, Class<? extends Annotation> annotation) {
        try {
            return obj.getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .isAnnotationPresent(annotation);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void log(String template, Object... args) {
        System.out.printf(template, args);
    }
}
