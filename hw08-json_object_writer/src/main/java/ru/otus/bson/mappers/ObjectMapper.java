package ru.otus.bson.mappers;

import ru.otus.bson.handlers.Handler;
import ru.otus.bson.info.ClassInfo;

import javax.json.JsonValue;

import static ru.otus.bson.utils.Predicates.isNull;

public class ObjectMapper implements Mapper<JsonValue> {

    private final Handler<ClassInfo> handler;

    public ObjectMapper(Handler<ClassInfo> handler) {
        this.handler = handler;
    }

    @Override
    public JsonValue toValue(Object object) {
       return isNull.test(object)
               ? JsonValue.NULL
               : toJsonValue(object, handler);
    }

    private JsonValue toJsonValue(Object object, Handler<ClassInfo> handler) {
        final ClassInfo classInfo = handler.handle(object);
        return toJsonValue(classInfo);
    }

    private JsonValue toJsonValue(ClassInfo classInfo) {
        return classInfo.asJsonValue();
    }
}