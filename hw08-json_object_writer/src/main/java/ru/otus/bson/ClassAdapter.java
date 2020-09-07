package ru.otus.bson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

public class ClassAdapter implements Adapter {

    private static final String EMPTY_JSON = "{}";

    @Override
    public String transform(Class<?> clazz) {
        if (clazz == null) {
            return EMPTY_JSON;
        }
        final Field[] fields = clazz.getDeclaredFields();
        return transform(fields);
    }

    private String transform(Field... fields) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(fields).forEach(field -> {
            System.out.println(field.getName() + " - " + field.getType() + " : " + field.getType().isInstance(Collection.class));
            System.out.println(field.getType().isPrimitive());

        });
        return stringBuilder.toString();
    }
}
