package ru.otus.bson;

public class JsonPrimitives implements JsonType {

    public JsonPrimitives() {}

    public boolean isTypeWith(Attributes attributes) {
        return attributes.getDeclaredClass().isPrimitive();
    }

    public <T> Class<?> getClass(Class<T> type) {
        if (type == byte.class) return Byte.class;
        if (type == short.class) return Short.class;
        if (type == int.class) return Integer.class;
        if (type == long.class) return Long.class;
        if (type == float.class) return Float.class;
        if (type == double.class) return Double.class;
        if (type == char.class) return Character.class;
        return Void.class;
    }
}
