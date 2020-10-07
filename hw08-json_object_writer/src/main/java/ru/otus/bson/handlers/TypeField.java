package ru.otus.bson.handlers;

import ru.otus.bson.jsonTypes.JsonArrayBuilder;
import ru.otus.bson.jsonTypes.JsonNumber;
import ru.otus.bson.jsonTypes.JsonObjectBuilder;
import ru.otus.bson.jsonTypes.JsonString;
import ru.otus.bson.utils.Predicates;

import javax.json.JsonValue;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

import static ru.otus.bson.utils.Predicates.*;

public enum TypeField {

    NULL {
        @Override
        public boolean isType(Class<?> clazz) {
            return clazz == null;
        }

        @Override
        public JsonValue toJson(Object object) {
            return JsonValue.NULL;
        }
    },
    NUMBER {
        @Override
        public boolean isType(Class<?> clazz) {
            return isNumber.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return new JsonNumber((Number) object);
        }
    },
    BOOLEAN {
        @Override
        public boolean isType(Class<?> clazz) {
            return isBoolean.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return (boolean) object ? JsonValue.TRUE : JsonValue.FALSE;
        }
    },
    CHAR {
        @Override
        public boolean isType(Class<?> clazz) {
            return isChar.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return STRING.toJson(object);
        }
    },
    ENUM {
        @Override
        public boolean isType(Class<?> clazz) {
            return false;
        }

        @Override
        public JsonValue toJson(Object object) {
            return STRING.toJson(object);
        }
    },
    STRING {
        @Override
        public boolean isType(Class<?> clazz) {
            return isString.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            return new JsonString(String.valueOf(object));
        }
    },
    ARRAY {
        @Override
        public boolean isType(Class<?> clazz) {
            return isArray.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            var builder = new JsonArrayBuilder();
            for (int idx = 0; idx < Array.getLength(object); idx++) {
                builder.add(ClassHandler.handle(Array.get(object, idx)));
            }
            return builder.build();
        }
    },
    COLLECTION {
        @Override
        public boolean isType(Class<?> clazz) {
            return isCollection.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            var builder = new JsonArrayBuilder();
            for (Object obj : (Collection<?>) object) {
                builder.add(ClassHandler.handle(obj));
            }
            return builder.build();
        }
    },
    OBJECT {
        @Override
        public boolean isType(Class<?> clazz) {
            return isObject.test(clazz);
        }

        @Override
        public JsonValue toJson(Object object) {
            var builder = new JsonObjectBuilder();
            for (Field field : ClassHandler.getFields(object)) {
                String fieldName = field.getName();
                if (!Predicates.isSerializableField.test(field)) continue;
                final Object valueField = ClassHandler.getValueField(object, field);
                if (valueField==null) continue;
                builder.add(fieldName, toJson(valueField));
            }
            return builder.build();
        }
    };

    public abstract boolean isType(Class<?> clazz);

    public abstract JsonValue toJson(Object object);

}
