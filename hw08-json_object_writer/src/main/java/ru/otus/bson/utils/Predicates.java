package ru.otus.bson.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static java.lang.reflect.Modifier.isStatic;
import static java.lang.reflect.Modifier.isTransient;

public final class Predicates {

    public static Predicate<Object> isNull = Objects::isNull;

    public static Predicate<Field> isSerializableField = filed -> {
        final int modifiers = filed.getModifiers();
        return !(isStatic(modifiers) || isTransient(modifiers));
    };

    public static Predicate<Class<?>> isNumber = clazz -> List.of(
            byte.class, Byte.class,
            short.class, Short.class,
            int.class, Integer.class,
            long.class, Long.class,
            float.class, Float.class,
            double.class, Double.class).contains(clazz);

    public static Predicate<Class<?>> isChar = clazz -> List.of(
            char.class, Character.class).contains(clazz);

    public static Predicate<Class<?>> isBoolean = clazz -> List.of(
            boolean.class, Boolean.class).contains(clazz);

    public static Predicate<Class<?>> isString = String.class::equals;

    public static Predicate<Class<?>> isArray = Class::isArray;

    public static Predicate<Class<?>> isCollection = Collection.class::isAssignableFrom;

    public static Predicate<Class<?>> isEnum = Class::isEnum;

    public static Predicate<Class<?>> isObject = clazz ->
                    !isNumber.test(clazz) &&
                    !isBoolean.test(clazz) &&
                    !isChar.test(clazz) &&
                    !isString.test(clazz) &&
                    !isArray.test(clazz) &&
                    !isCollection.test(clazz);
}
