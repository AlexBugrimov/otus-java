package ru.otus.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LogInvocationHandler implements InvocationHandler {

    private final Object target;

    LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (LoggingAnnotation.isPresentAnnotation(target, method, Log.class)) {
            LoggingAnnotation.log(
                    "executed method: %s, params: %s%n",
                    method.getName(), Arrays.stream(args)
                            .map(Object::toString)
                            .collect(Collectors.joining(", "))
            );
        }
        return method.invoke(target, args);
    }
}