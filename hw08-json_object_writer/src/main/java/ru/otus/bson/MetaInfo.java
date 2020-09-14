package ru.otus.bson;

import ru.otus.bson.exceptions.BsonException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MetaInfo {

    private final Map<Field, MetaInfo> dataFields = new HashMap<>();
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

    public static MetaInfo from(Object object, Field field) {
        return null;
    }

    public Attributes getFiledAttributes() {
        return attributes;
    }

    public Object getFieldValue() {
        return value;
    }
}
