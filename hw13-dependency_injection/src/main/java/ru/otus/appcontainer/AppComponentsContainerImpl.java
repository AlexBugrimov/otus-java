package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final Map<Class<?>, Object> appComponentsByClass = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponentsByClass.get(componentClass);
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
            final AppComponent appComponent = method.getDeclaredAnnotation(AppComponent.class);
            final Class<?> type = method.getReturnType();
            final Object[] args = getArgsForMethod(method);
            try {
                final Object invoke = method.invoke(instance, args);
                appComponentsByName.put(appComponent.name(), invoke);
                appComponentsByClass.put(type, invoke);
                appComponentsByClass.put(invoke.getClass(), invoke);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new MethodInvokeException(e);
            }
        }
    }

    private Object[] getArgsForMethod(Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final int parameterCount = parameterTypes.length;
        if (parameterCount == 0) {
            return new Object[0];
        }
        return Stream.of(parameterTypes)
                .filter(appComponentsByClass::containsKey)
                .map(appComponentsByClass::get).toArray();
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
