package ru.otus.bson;

import ru.otus.bson.JsonNode;

public interface Handler {

    Result process(Object object);
}
