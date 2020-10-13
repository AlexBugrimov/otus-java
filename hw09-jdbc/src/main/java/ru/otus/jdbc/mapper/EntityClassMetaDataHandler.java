package ru.otus.jdbc.mapper;

import ru.otus.core.exceptions.ClassMetaDataException;
import ru.otus.jdbc.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EntityClassMetaDataHandler<T> implements EntityClassMetaData<T> {

    private static final Predicate<Field> FIELD_ID_PREDICATE = field -> field.isAnnotationPresent(Id.class);
    private final Class<T> clazz;
    private final Field[] fields;

    private EntityClassMetaDataHandler(Class<T> clazz, Field[] fields) {
        this.clazz = clazz;
        this.fields = fields;
    }

    public static <T> EntityClassMetaData<T> of(Class<T> clazz) {
        return new EntityClassMetaDataHandler<>(clazz, clazz.getFields());
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
}
