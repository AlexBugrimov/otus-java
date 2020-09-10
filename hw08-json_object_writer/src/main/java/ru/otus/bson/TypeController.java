package ru.otus.bson;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;

public class TypeController {

    public FiledAttributes getMetaData(Object object, Field field) {
        final boolean isInterface = field.getType().isInterface();
        final boolean isArray = field.getType().isArray();
        final boolean isPrimitive = field.getType().isPrimitive();
        final boolean isEnum = field.getType().isEnum();
        final boolean isCollection = Collection.class.isAssignableFrom(field.getType());
        final boolean isClass = Class.class.isAssignableFrom(field.getType());
        final FiledAttributes filedAttributes = new FiledAttributes(field);
        return filedAttributes;
    }

    public static class FiledAttributes {

        private final Field field;

        public FiledAttributes(Field field) {
            this.field = field;
        }

        public String getName() {
            return field.getName();
        }

        public Type getDeclaredType() {
            return field.getGenericType();
        }

        public Class<?> getDeclaredClass() {
            return field.getType();
        }
    }
}
