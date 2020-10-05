package ru.otus.bson.info;

import static ru.otus.bson.utils.Predicates.*;

public enum TypeField {

    NUMBER {
        @Override
        public boolean isType(Class<?> clazz) {
            return isNumber.test(clazz);
        }
    },
    BOOLEAN {
        @Override
        public boolean isType(Class<?> clazz) {
            return isBoolean.test(clazz);
        }
    },
    CHAR {
        @Override
        public boolean isType(Class<?> clazz) {
            return isChar.test(clazz);
        }
    },
    STRING {
        @Override
        public boolean isType(Class<?> clazz) {
            return isString.test(clazz);
        }
    },
    ARRAY {
        @Override
        public boolean isType(Class<?> clazz) {
            return isArray.test(clazz);
        }
    },
    COLLECTION {
        @Override
        public boolean isType(Class<?> clazz) {
            return isCollection.test(clazz);
        }
    },
    OBJECT {
        @Override
        public boolean isType(Class<?> clazz) {
            return isObject.test(clazz);
        }
    };

    public abstract boolean isType(Class<?> clazz);
}
