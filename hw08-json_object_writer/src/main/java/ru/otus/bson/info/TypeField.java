package ru.otus.bson.info;

import static ru.otus.bson.utils.Predicates.*;

public enum TypeField {

    NUMBER {
        @Override
        public boolean isType(Object object) {
            return isNumber.test(object);
        }
    },
    BOOLEAN {
        @Override
        public boolean isType(Object object) {
            return isBoolean.test(object);
        }
    },
    CHAR {
        @Override
        public boolean isType(Object object) {
            return isChar.test(object);
        }
    },
    STRING {
        @Override
        public boolean isType(Object object) {
            return isString.test(object);
        }
    },
    ARRAY {
        @Override
        public boolean isType(Object object) {
            return isArray.test(object);
        }
    },
    COLLECTION {
        @Override
        public boolean isType(Object object) {
            return isCollection.test(object);
        }
    },
    OBJECT {
        @Override
        public boolean isType(Object object) {
            return isObject.test(object);
        }
    };

    public abstract boolean isType(Object object);
}
