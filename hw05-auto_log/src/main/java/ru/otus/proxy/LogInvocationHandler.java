package ru.otus.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LogInvocationHandler implements InvocationHandler {

    private final Object target;
    private final List<Method> logMethods;

    LogInvocationHandler(Object target) {
        this.target = target;
        this.logMethods = getMethodsBy(target, (method) -> method.isAnnotationPresent(Log.class));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logInfo(method, args);
        return method.invoke(target, args);
    }

    private void logInfo(Method method, Object[] args) {
        final String methodName = method.getName();
        final Class<?>[] parameterTypes = method.getParameterTypes();
        for (Method logMethod : logMethods) {
            if (methodName.equals(logMethod.getName())
                    && Arrays.equals(parameterTypes, logMethod.getParameterTypes())) {
                System.out.printf("executed method: %s, params: %s%n", logMethod.getName(), argsAsString(args));
                return;
            }
        }
    }

    private List<Method> getMethodsBy(Object target, Predicate<Method> methodPredicate) {
        return Arrays.stream(target.getClass().getMethods())
                .filter(methodPredicate)
                .collect(Collectors.toList());
    }

    private String argsAsString(Object[] args) {
        return Arrays.stream(args)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}