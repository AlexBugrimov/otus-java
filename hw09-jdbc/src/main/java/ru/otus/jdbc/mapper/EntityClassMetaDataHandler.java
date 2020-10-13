package ru.otus.jdbc.mapper;

import ru.otus.core.exceptions.JdbcException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataHandler<T> implements EntityClassMetaData<T> {

    private final Class<T> clazz;

    private EntityClassMetaDataHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> EntityClassMetaData<T> of(Class<T> clazz) {
        return new EntityClassMetaDataHandler<>(clazz);
    }

    @Override
    public String getName() {
        return clazz.getName();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new JdbcException(e.getMessage(), e);
        }
    }

    @Override
    public Field getIdField() {
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.asList(clazz.getFields().clone());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return null;
    }
}
