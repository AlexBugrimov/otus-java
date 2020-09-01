package ru.otus.proxy;

import java.lang.reflect.Proxy;

public class Ioc {

    private Ioc() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T createWithLogging(Class<T> itf, T target) {
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class<?>[]{itf},
                new LogInvocationHandler(target)
        );
    }
}
