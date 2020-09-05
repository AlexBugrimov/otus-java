package ru.otus.writer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

public class Bson {

    public String toJson(Object object) {

        final Field[] fields = object.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            System.out.println(field.getName() + " - " + field.getType() + " : " + field.getType().isInstance(Collection.class));
            System.out.println(field.getType().isPrimitive());

        });
        return "Object";
    }
}
