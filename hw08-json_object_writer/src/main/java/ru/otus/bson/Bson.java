package ru.otus.bson;

import ru.otus.bson.handlers.ObjectHandler;

import static ru.otus.bson.handlers.Predicates.isNull;

public class Bson {

    public String toJson(Object object) {
        return isNull.test(object)
                ? "{}" :
                ObjectHandler.handle(object).toString();
    }
}
