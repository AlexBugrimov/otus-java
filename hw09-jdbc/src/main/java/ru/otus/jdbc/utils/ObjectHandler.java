package ru.otus.jdbc.utils;

import ru.otus.jdbc.exceptions.JdbcException;
import ru.otus.jdbc.mapper.EntityClassMetaData;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class ObjectHandler {

    private ObjectHandler() {
    }

    public static <T> T build(ResultSet resultSet, EntityClassMetaData<T> entityClassMetaData) {
        T instance = getInstance(entityClassMetaData);

        entityClassMetaData.getAllFields().forEach(field ->
                fillInstance(instance, field, resultSet)
        );

        return instance;
    }

    private static <T> void fillInstance(T instance, Field field, ResultSet resultSet) {
        final String fieldName = field.getName();
        try {
            field.setAccessible(true);
            field.set(instance, resultSet.getObject(fieldName));
            field.setAccessible(false);
        } catch (IllegalAccessException | SQLException e) {
            throw new JdbcException("Error getting the object: " + fieldName, e);
        }
    }

    private static <T> T getInstance(EntityClassMetaData<T> entityClassMetaData) {
        try {
            return entityClassMetaData.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new JdbcException("Error creating an instance", e);
        }
    }

    public static <T> T getEntity(EntityClassMetaData<T> entityClassMetaData, T object) {
        try {
            final Field field = entityClassMetaData.getIdField();
            field.setAccessible(true);
            final T entity = (T) field.get(object);
            field.setAccessible(false);
            return entity;
        } catch (IllegalAccessException e) {
            throw new JdbcException("Error getting the object", e);
        }
    }
}
