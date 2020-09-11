package ru.otus.bson;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class FieldAttributes implements Attributes {

    private final String fieldName;
    private final Type type;
    private final Class<?> declaredClass;

    public FieldAttributes(Field field) {
        this.fieldName = field.getName();
        this.type = field.getGenericType();
        this.declaredClass = field.getDeclaringClass();
    }

    @Override
    public String getName() {
        return fieldName;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Class<?> getDeclaredClass() {
        return declaredClass;
    }
}
