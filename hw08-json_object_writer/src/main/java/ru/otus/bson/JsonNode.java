package ru.otus.bson;

public abstract class JsonNode {

    public abstract JsonNode copy();

    protected boolean isPrimitive() {
        return this instanceof JsonPrimitives;
    }

    protected boolean isObject() {
        return this instanceof JsonObject;
    }

    protected boolean isArray() {
        return this instanceof JsonArray;
    }

    public boolean isNull() {
        return this instanceof JsonNull;
    }
}
