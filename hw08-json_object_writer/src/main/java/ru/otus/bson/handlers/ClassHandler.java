package ru.otus.bson.handlers;

import ru.otus.bson.exceptions.BsonException;
import ru.otus.bson.jsonTypes.JsonArrayBuilder;
import ru.otus.bson.jsonTypes.JsonNumber;
import ru.otus.bson.jsonTypes.JsonObjectBuilder;
import ru.otus.bson.jsonTypes.JsonString;
import ru.otus.bson.utils.Predicates;

import javax.json.JsonValue;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ru.otus.bson.handlers.TypeField.*;

public class ClassHandler {

    public static JsonValue handle(Object object) {
//
        final Class<?> aClass = object.getClass();
//
//        for (TypeField typeField : TypeField.values()) {
//            if (typeField.isType(aClass)) {
//                return typeField.handle(object);
//            }
//        }
//        return ClassHandler.handle(object);
        return switch (aClass) {
            case Number.class: NUMBER.toJson(object);
            case String.class: STRING.toJson(object);
        };
//        var builder = new JsonObjectBuilder();
//        if (NULL.isType(aClass)) {
//            return NULL.toJson(object);
//        } else if (Predicates.isNumber.test(object.getClass())) {
//            return new JsonNumber((Number) object);
//        } else if (Predicates.isBoolean.test(object.getClass())) {
//            return (boolean) object ? JsonValue.TRUE : JsonValue.FALSE;
//        } else if (Predicates.isChar.test(object.getClass())) {
//            return new JsonString(String.valueOf(object));
//        } else if (Predicates.isString.test(object.getClass()) || Predicates.isEnum.test(object.getClass())) {
//            return new JsonString(String.valueOf(object));
//        } else if (Predicates.isArray.test(object.getClass())) {
//            var jsonArrayBuilder = new JsonArrayBuilder();
//            int length = Array.getLength(object);
//            for (int i = 0; i < length; i++) {
//                jsonArrayBuilder.add(handle(Array.get(object, i)));
//            }
//            return jsonArrayBuilder.build();
//        } else if (Predicates.isCollection.test(object.getClass())) {
//            var jsonArrayBuilder = new JsonArrayBuilder();
//            for (Object o : (Collection<?>) object) {
//                jsonArrayBuilder.add(handle(o));
//            }
//            return jsonArrayBuilder.build();
//        } else {
//            for (Field field : getFields(object)) {
//                String fieldName = field.getName();
//                if (!Predicates.isSerializableField.test(field)) continue;
//                final Object valueField = getValueField(object, field);
//                if (valueField==null) continue;
//                builder.add(fieldName, handle(valueField));
//            }
//        }
//        return builder.build();
    }

    public static Object getValueField(Object object, Field field) {
        try {
            field.setAccessible(true);
            final Object value = field.get(object);
            field.setAccessible(false);
            return value;
        } catch (IllegalAccessException e) {
            throw new BsonException("Error getting value from field: " + field.getName(), e);
        }
    }

    public static List<Field> getFields(Object object) {
        return Arrays.asList(object.getClass().getDeclaredFields().clone());
    }

}
