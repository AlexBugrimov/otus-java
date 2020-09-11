package ru.otus.bson;

import ru.otus.bson.exceptions.BsonException;

import java.lang.reflect.Field;

public class MetaInfo implements FieldInfo {

    private final Attributes attributes;
    private final Object value;

    public MetaInfo(Object object, Field field) {
        this.attributes = new FieldAttributes(field);
        try {
            this.value = field.get(object);
        } catch (IllegalAccessException e) {
            throw new BsonException("Error getting value from field", e);
        }
    }

    @Override
    public Attributes getFiledAttributes() {
        return attributes;
    }

    @Override
    public Object getFieldValue() {
        return value;
    }
}
