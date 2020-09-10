package ru.otus.bson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ClassAdapter implements Adapter {

    private static final String EMPTY_JSON = "{}";

    @Override
    public String transform(Object object) {
        if (object == null) {
            return EMPTY_JSON;
        }
        final Field[] fields = object.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .map(field -> asString(object, field))
                .collect(Collectors.joining(",", "{", "}"));
    }

    private String asString(Object object, Field field) {
        final TypeController typeController = new TypeController();
        try {
            field.setAccessible(true);
            final TypeController.FiledAttributes filedAttributes = typeController.getMetaData(object, field);

            final String result = '"' + field.getName() + '"' + ':' + field.get(object);
            field.setAccessible(false);
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }
}
