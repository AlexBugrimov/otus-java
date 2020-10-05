package ru.otus.bson.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.reflect.Modifier.isStatic;
import static java.lang.reflect.Modifier.isTransient;

public final class Predicates {

    public static Predicate<Object> isNull = Objects::isNull;

    public static Predicate<Field> isSerializableField = filed -> {
        final int modifiers = filed.getModifiers();
        return !isStatic(modifiers) || !isTransient(modifiers);
    };

    public static Predicate<Object> isNumber = object -> Stream.of(
            byte.class, Byte.class,
            short.class, Short.class,
            int.class, Integer.class,
            long.class, Long.class,
            float.class, Float.class,
            double.class, Double.class).anyMatch(type -> type.equals(object.getClass()));

    public static Predicate<Object> isChar = object -> Stream.of(
            char.class, Character.class).anyMatch(type -> type.equals(object.getClass()));

    public static Predicate<Object> isBoolean = object -> Stream.of(
            boolean.class, Boolean.class).anyMatch(type -> type.equals(object.getClass()));

    public static Predicate<Object> isString = object -> String.class.equals(object.getClass());

    public static Predicate<Object> isArray = object -> object.getClass().isArray();

    public static Predicate<Object> isCollection = object -> object instanceof Collection;

    public static Predicate<Object> isObject = object ->
                    !isNumber.test(object) &&
                    !isBoolean.test(object) &&
                    !isChar.test(object) &&
                    !isString.test(object) &&
                    !isArray.test(object) &&
                    !isCollection.test(object);


}
