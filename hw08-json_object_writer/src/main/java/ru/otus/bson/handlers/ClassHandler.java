package ru.otus.bson.handlers;

import ru.otus.bson.exceptions.BsonException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;
import static java.lang.reflect.Modifier.isTransient;

public class ClassHandler {

    private ClassHandler() {
    }

    public static List<Field> getFields(Object object) {
        return Arrays.asList(object.getClass().getDeclaredFields().clone());
    }

    public static Object getValueField(Object object, Field field) {
        try {
            field.setAccessible(true);
            final Object value = field.get(object);
            field.setAccessible(false);
            return value;
        } catch (IllegalAccessException e) {
            throw new BsonException("Error getting value from field: " + field.getName(), e);
        }
    }

    public static boolean isSerializableField(Field field) {
        final int modifiers = field.getModifiers();
        return !(isStatic(modifiers) || isTransient(modifiers));
    }
}
