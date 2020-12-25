package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.appcontainer.error.ClassNotFoundException;
import ru.otus.appcontainer.error.InstanceCreatingException;
import ru.otus.appcontainer.error.MethodInvokeException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final Set<Object> appComponents = new HashSet<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C> C getAppComponent(final Class<C> componentClass) {
        return (C) findObject(componentClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        final Object instance = getInstanceClass(configClass);
        for (Method method : getMethodsFrom(configClass)) {
            final String nameBean = nameBean(method);
            final Object[] args = getArgsForMethod(method);
            try {
                final Object executionResult = method.invoke(instance, args);
                appComponentsByName.put(nameBean, executionResult);
                appComponents.add(executionResult);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new MethodInvokeException(e);
            }
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    private Object getInstanceClass(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            throw new InstanceCreatingException(e);
        }
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

    private String nameBean(Method method) {
        final var annotate = method.getDeclaredAnnotation(AppComponent.class);
        return annotate.name();
    }

    private Object[] getArgsForMethod(Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final int parameterCount = parameterTypes.length;
        if (parameterCount == 0) {
            return new Object[0];
        }
        return Stream.of(parameterTypes)
                .map(this::findObject)
                .toArray();
    }

    private Object findObject(Class<?> param) {
        return appComponents.stream()
                .filter(component -> param.isAssignableFrom(component.getClass()))
                .findFirst()
                .orElseThrow(() -> new ClassNotFoundException("Class not found: " + param.getName()));
    }
}
