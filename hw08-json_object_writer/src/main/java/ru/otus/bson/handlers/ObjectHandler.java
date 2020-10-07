package ru.otus.bson.handlers;

import javax.json.JsonValue;

import static ru.otus.bson.handlers.ElementType.*;

public final class ObjectHandler {

    private ObjectHandler() {}

    public static JsonValue handle(Object object) {
        if (object == null) {
            return NULL.toJson(null);
        }
        final Class<?> aClass = object.getClass();
        if (NUMBER.isClass(aClass)) {
            return NUMBER.toJson(object);
        } else if (BOOLEAN.isClass(aClass)) {
            return BOOLEAN.toJson(object);
        } else if (ENUM.isClass(aClass) || CHAR.isClass(aClass) || STRING.isClass(aClass)) {
            return STRING.toJson(object);
        } else if (ARRAY.isClass(aClass)) {
            return ARRAY.toJson(object);
        } else if (COLLECTION.isClass(aClass)) {
            return COLLECTION.toJson(object);
        } else {
            return OBJECT.toJson(object);
        }
    }
}
