package ru.otus.bson;

import javax.json.JsonValue;

import static ru.otus.bson.utils.Predicates.isNull;

public class ObjectMapper implements Mapper {

    private final Handler handler;

    public ObjectMapper(Handler handler) {
        this.handler = handler;
    }

    public JsonValue toJsonValue(Object object) {
       return isNull.test(object)
               ? JsonValue.NULL
               : toJsonValue(object, handler);
    }

    private JsonValue toJsonValue(Object object, Handler handler) {
        final Result result = handler.process(object);
        return null;
    }
}