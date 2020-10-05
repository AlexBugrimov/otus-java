package ru.otus.bson.handlers;

import ru.otus.bson.exceptions.BsonException;
import ru.otus.bson.info.ClassInfo;
import ru.otus.bson.info.ClassMetaInfo;
//import ru.otus.bson.info.ClassInfo;
//import ru.otus.bson.info.ClassMetaInfo;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.otus.bson.utils.Predicates.isSerializableField;

public class ClassHandler implements Handler<ClassInfo> {

    @Override
    public ClassInfo handle(final Object object) {
        final ClassInfo metaInfo = new ClassMetaInfo();
        final Field[] fields = getFields(object);
        final List<Object> objects = Stream.of(fields)
                .filter(isSerializableField)
                .map(field -> {
                    try {
                        return field.get(object);
                    } catch (IllegalAccessException e) {
                        throw new BsonException("Error getting value from field: " + field.getName(), e);
                    }
                }).collect(Collectors.toUnmodifiableList());

        return metaInfo;
    }

    private Field[] getFields(Object object) {
        return object.getClass().getDeclaredFields();
    }

}
