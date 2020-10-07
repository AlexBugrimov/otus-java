package ru.otus.bson;

import ru.otus.bson.handlers.ObjectHandler;

import javax.json.JsonValue;

import static ru.otus.bson.handlers.Predicates.isNull;

public class Bson {

    public String toJson(Object object) {
        final JsonValue jsonValue = ObjectHandler.handle(object);
        return isNull.test(jsonValue) ? "{}" : jsonValue.toString();
    }
}
