package ru.otus.bson.mappers;

import ru.otus.bson.handlers.ClassHandler;

import javax.json.JsonValue;

import static ru.otus.bson.utils.Predicates.isNull;

public class ObjectMapper implements Mapper<JsonValue> {

    @Override
    public JsonValue toValue(Object object) {
       return isNull.test(object)
               ? JsonValue.NULL
               : ClassHandler.handle(object);
    }
}