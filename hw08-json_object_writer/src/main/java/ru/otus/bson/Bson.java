package ru.otus.bson;

import ru.otus.bson.handlers.ObjectHandler;

public class Bson {

    public String toJson(Object object) {
        return ObjectHandler.handle(object).toString();
    }
}
