package ru.otus.bson.handlers;

import ru.otus.bson.exceptions.BsonException;
import ru.otus.bson.jsonTypes.JsonArrayBuilder;
import ru.otus.bson.jsonTypes.JsonNumber;
import ru.otus.bson.jsonTypes.JsonObjectBuilder;
import ru.otus.bson.jsonTypes.JsonString;

import javax.json.JsonValue;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ru.otus.bson.handlers.Conditions.*;

public enum ElementType {

    NULL {
        @Override
        public boolean isClass(Class<?> clazz) {
            return clazz == null;
        }

        @Override
        public JsonValue toJson(Object object) {
            return JsonValue.NULL;
        }
    },
    NUMBER {
        @Override
        public boolean isClass(Class<?> clazz) {
            return isNumber.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return new JsonNumber((Number) object);
        }
    },
    BOOLEAN {
        @Override
        public boolean isClass(Class<?> clazz) {
            return isBoolean.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return (boolean) object ? JsonValue.TRUE : JsonValue.FALSE;
        }
    },
    CHAR {
        @Override
        public boolean isClass(Class<?> clazz) {
            return isChar.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return STRING.toJson(object);
        }
    },
    ENUM {
        @Override
        public boolean isClass(Class<?> clazz) {
            return isEnum.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return STRING.toJson(object);
        }
    },
    STRING {
        @Override
        public boolean isClass(Class<?> clazz) {
            return isString.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return new JsonString(String.valueOf(object));
        }
    },
    ARRAY {
        @Override
        public boolean isClass(Class<?> clazz) {
            return isArray.test(clazz);
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
            return isCollection.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            var builder = new JsonArrayBuilder();
            for (Object obj : (Collection<?>) object) {
                builder.add(ObjectHandler.handle(obj));
            }
            return builder.build();
        }
    },
    OBJECT {
        @Override
        public boolean isClass(Class<?> clazz) {
            return isObject.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            var builder = new JsonObjectBuilder();
            for (Field field : getFields(object)) {
                if (!Conditions.isSerializableField.test(field)) continue;
                final Object value = getValueField(object, field);
                if (value == null) continue;
                builder.add(field.getName(), ObjectHandler.handle(value));
            }
            return builder.build();
        }

        private Object getValueField(Object object, Field field) {
            try {
                field.setAccessible(true);
                final Object value = field.get(object);
                field.setAccessible(false);
                return value;
            } catch (IllegalAccessException e) {
                throw new BsonException("Error getting value from field: " + field.getName(), e);
            }
        }

        private List<Field> getFields(Object object) {
            return Arrays.asList(object.getClass().getDeclaredFields().clone());
        }
    };

    public abstract boolean isClass(Class<?> clazz);

    public abstract JsonValue toJson(Object object);

}
