package ru.otus.bson.handlers;

import ru.otus.bson.exceptions.BsonException;
import ru.otus.bson.info.ClassInfo;
import ru.otus.bson.info.ClassMetaInfo;
import ru.otus.bson.info.FieldInfo;
import ru.otus.bson.info.TypeField;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static ru.otus.bson.utils.Predicates.isSerializableField;

public class ClassHandler implements Handler<ClassInfo> {

    @Override
    public ClassInfo handle(final Object object) {
        final ClassInfo metaInfo = new ClassMetaInfo();
        getFields(object).forEach(field -> {
            if (isSerializableField.test(field)) {
                for (TypeField typeField : TypeField.values()) {
                    if (typeField.isType(field.getType())) {
                        metaInfo.addFieldInfo(
                                FieldInfo.from(typeField, field.getName(), getValueField(object, field))
                        );
                    }
                }
            }
        });
        return metaInfo;
    }

    private Object getValueField(Object object, Field field) {
        try {
            field.setAccessible(true);
            final Object value = field.get(object);
            field.setAccessible(false);
            return value;
        } catch (IllegalAccessException e) {
            throw new BsonException("Error getting value from field: " + field.getName(), e);
        }
    }

    private List<Field> getFields(Object object) {
        return Arrays.asList(object.getClass().getDeclaredFields().clone());
    }

}
