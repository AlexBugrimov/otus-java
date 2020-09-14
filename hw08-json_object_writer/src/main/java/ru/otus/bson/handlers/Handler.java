package ru.otus.bson.handlers;

import ru.otus.bson.JsonNode;

public interface Handler {

    void write(JsonNode node);
}
