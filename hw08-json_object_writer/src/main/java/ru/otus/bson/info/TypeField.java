package ru.otus.bson.info;

import ru.otus.bson.types.JsonNumber;
import ru.otus.bson.types.JsonString;

import javax.json.JsonValue;

import static ru.otus.bson.utils.Predicates.*;

public enum TypeField {

    NUMBER {
        @Override
        public boolean isType(Class<?> clazz) {
            return isNumber.test(clazz);
        }

        @Override
        public JsonValue getJsonValue(Object object) {
            return new JsonNumber((Number) object);
        }
    },
    BOOLEAN {
        @Override
        public boolean isType(Class<?> clazz) {
            return isBoolean.test(clazz);
        }

        @Override
        public JsonValue getJsonValue(Object object) {
            return (boolean)object ? JsonValue.TRUE : JsonValue.FALSE;
        }
    },
    CHAR {
        @Override
        public boolean isType(Class<?> clazz) {
            return isChar.test(clazz);
        }

        @Override
        public JsonValue getJsonValue(Object object) {
            return STRING.getJsonValue(object);
        }
    },
    STRING {
        @Override
        public boolean isType(Class<?> clazz) {
            return isString.test(clazz);
        }

        @Override
        public JsonValue getJsonValue(Object object) {
            return new JsonString(String.valueOf(object));
        }
    },
    ARRAY {
        @Override
        public boolean isType(Class<?> clazz) {
            return isArray.test(clazz);
        }

        @Override
        public JsonValue getJsonValue(Object object) {
            return null;
        }
    },
    COLLECTION {
        @Override
        public boolean isType(Class<?> clazz) {
            return isCollection.test(clazz);
        }

        @Override
        public JsonValue getJsonValue(Object object) {
            return null;
        }
    },
    OBJECT {
        @Override
        public boolean isType(Class<?> clazz) {
            return isObject.test(clazz);
        }

        @Override
        public JsonValue getJsonValue(Object object) {
            return null;
        }
    };

    public abstract boolean isType(Class<?> clazz);
    public abstract JsonValue getJsonValue(Object object);
}
