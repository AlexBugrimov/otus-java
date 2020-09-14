package ru.otus.bson;

public class JsonNull extends JsonNode {

    public static final JsonNode VALUE = new JsonNull();

    @Override
    public JsonNode copy() {
        return VALUE;
    }
}
