package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final SortedMap<String, Object> appComponentsByName = new TreeMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        final Object instance = getInstanceClass(configClass);

        for (Method method : getMethodsFrom(configClass)) {
            final AppComponent appComponent = method.getDeclaredAnnotation(AppComponent.class);
            final Class<?>[] args = getArgsMethod(method);
            final Class<?> aClass = method.getReturnType();
            try {
                final Object invoke = method.invoke(instance, args);
                appComponentsByName.put(appComponent.name(), invoke);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private Class<?>[] getArgsMethod(Method method) {
        return method.getParameterTypes();
    }

    private Object getInstanceClass(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        final String className = componentClass.getName();
        return getAppComponent(className);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        try {
            return (C) appComponentsByName.get(componentName.toLowerCase()).getClass().getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Method> getMethodsFrom(Class<?> clazz) {
        final Predicate<Method> isAppComponent = method -> method.isAnnotationPresent(AppComponent.class);
        final Comparator<Method> orderComparator = Comparator.comparingInt(
                method -> method.getAnnotation(AppComponent.class).order());
        return Stream.of(clazz.getDeclaredMethods())
                .filter(isAppComponent)
                .sorted(orderComparator)
                .collect(Collectors.toList());
    }

}
