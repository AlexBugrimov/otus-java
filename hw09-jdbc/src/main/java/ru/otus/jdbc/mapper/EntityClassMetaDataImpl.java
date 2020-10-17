package ru.otus.jdbc.mapper;

import ru.otus.jdbc.exceptions.ClassMetaDataException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private static final Predicate<Field> FIELD_ID_PREDICATE = field -> field.isAnnotationPresent(Id.class);
    private final Class<T> clazz;
    private final Field[] fields;

    private EntityClassMetaDataImpl(Class<T> clazz, Field[] fields) {
        this.clazz = clazz;
        this.fields = fields;
    }

    public static <T> EntityClassMetaData<T> of(Class<T> clazz) {
        return new EntityClassMetaDataImpl<>(clazz, clazz.getDeclaredFields());
    }

    @Override
    public String getName() {
        return clazz.getSimpleName().toLowerCase();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new ClassMetaDataException(e);
        }
    }

    @Override
    public Field getIdField() {
        return getAllFields().stream()
                .filter(FIELD_ID_PREDICATE)
                .findFirst()
                .orElseThrow(() -> new ClassMetaDataException("No field marked with annotation @Id"));
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.asList(fields.clone());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return getAllFields().stream()
                .filter(FIELD_ID_PREDICATE.negate())
                .collect(Collectors.toList());
    }

    @Override
    public List<Object> getValues(T object) {
        final List<Object> params = new LinkedList<>();
        for (Field field : getFieldsWithoutId()) {
            try {
                field.setAccessible(true);
                final Object param = field.get(object);
                field.setAccessible(false);
                params.add(param);
            } catch (IllegalAccessException e) {
                throw new ClassMetaDataException(e);
            }
        }
        return params;
    }
}
