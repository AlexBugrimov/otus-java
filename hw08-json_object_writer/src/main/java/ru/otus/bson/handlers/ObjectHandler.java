package ru.otus.bson.handlers;

import ru.otus.bson.types.JsonObjectBuilder;

import javax.json.JsonValue;
import java.lang.reflect.Field;

import static ru.otus.bson.handlers.ClassHandler.*;
import static ru.otus.bson.handlers.ElementType.values;

public final class ObjectHandler {

    private ObjectHandler() {
    }

    public static JsonValue handle(Object object) {
        if (object == null) {
            return JsonValue.NULL;
        }
        for (ElementType elementType : values()) {
            if (elementType.isClass(object.getClass())) {
                return elementType.toJson(object);
            }
        }
        return toJson(object);
    }

    private static JsonValue toJson(Object object) {
        var builder = new JsonObjectBuilder();
        for (Field field : getFields(object)) {
            if (isSerializableField(field)) {
                final Object value = getValueField(object, field);
                if (value != null) {
                    builder.add(field.getName(), handle(value));
                }
            }
        }
        return builder.build();
    }
}
