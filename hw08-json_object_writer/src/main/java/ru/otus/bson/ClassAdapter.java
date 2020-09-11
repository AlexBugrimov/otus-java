package ru.otus.bson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassAdapter implements Adapter {

    private static final String EMPTY_JSON = "{}";
    private Map<Field, MetaInfo> fields = new HashMap<>();

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
        final MetaInfo metaInfo = new MetaInfo(object, field);
        StringBuilder sb = new StringBuilder();
        try {
            field.setAccessible(true);
            fields.put(field, new MetaInfo(object, field));
            final Attributes fieldAttributes = metaInfo.getFiledAttributes();
            final JsonType jsonPrimitives = new JsonPrimitives();
            final boolean isPrimitive = jsonPrimitives.isTypeWith(fieldAttributes);
            final Class<?> primitivesClass = jsonPrimitives.getClass(fieldAttributes.getDeclaredClass());
            if (isPrimitive) {
                sb.append('"').append(field.getName()).append('"').append(':').append(field.get(object));
            }
            final String result = '"' + field.getName() + '"' + ':' + field.get(object);
            field.setAccessible(false);
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }
}

/*
*         final boolean isInterface = field.getType().isInterface();
        final boolean isArray = field.getType().isArray();
        final boolean isPrimitive = field.getType().isPrimitive();
        final boolean isEnum = field.getType().isEnum();
        final boolean isCollection = Collection.class.isAssignableFrom(field.getType());
        final boolean b = field.getType() instanceof Object;

//        final boolean isClass = Class.class.isAssignableFrom(field.getType());
* */
