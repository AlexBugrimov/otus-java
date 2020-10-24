package ru.otus.bson.handlers;

import ru.otus.bson.types.JsonArrayBuilder;
import ru.otus.bson.types.JsonNumber;
import ru.otus.bson.types.JsonString;

import javax.json.JsonValue;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

public enum ElementType {
    NUMBER {
        @Override
        public boolean isClass(Class<?> clazz) {
            return List.of(
                    byte.class, Byte.class,
                    short.class, Short.class,
                    int.class, Integer.class,
                    long.class, Long.class,
                    float.class, Float.class,
                    double.class, Double.class).contains(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return new JsonNumber((Number) object);
        }
    },
    BOOLEAN {
        @Override
        public boolean isClass(Class<?> clazz) {
            return List.of(boolean.class, Boolean.class).contains(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return (boolean) object ? JsonValue.TRUE : JsonValue.FALSE;
        }
    },
    CHAR {
        @Override
        public boolean isClass(Class<?> clazz) {
            return List.of(char.class, Character.class).contains(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return STRING.toJson(object);
        }
    },
    ENUM {
        @Override
        public boolean isClass(Class<?> clazz) {
            return clazz.isEnum();
        }

        @Override
        public JsonValue toJson(Object object) {
            return STRING.toJson(object);
        }
    },
    STRING {
        @Override
        public boolean isClass(Class<?> clazz) {
            return String.class.equals(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return new JsonString(String.valueOf(object));
        }
    },
    ARRAY {
        @Override
        public boolean isClass(Class<?> clazz) {
            return clazz.isArray();
        }

        @Override
        public JsonValue toJson(Object object) {
            var builder = new JsonArrayBuilder();
            for (int idx = 0; idx < Array.getLength(object); idx++) {
                builder.add(ObjectHandler.handle(Array.get(object, idx)));
            }
            return builder.build();
        }
    },
    COLLECTION {
        @Override
        public boolean isClass(Class<?> clazz) {
            return Collection.class.isAssignableFrom(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            var builder = new JsonArrayBuilder();
            for (Object obj : (Collection<?>) object) {
                builder.add(ObjectHandler.handle(obj));
            }
            return builder.build();
        }
    };

    public abstract boolean isClass(Class<?> clazz);

    public abstract JsonValue toJson(Object object);

}
