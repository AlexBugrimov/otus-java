package ru.otus.bson.types;

import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;

public class JsonObjectBuilder implements javax.json.JsonObjectBuilder {

    private final JsonObject jsonObject = new JsonObject(new LinkedHashMap<>());

    @Override
    public javax.json.JsonObjectBuilder add(String name, JsonValue value) {
        jsonObject.put(name, value);
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder add(String name, String value) {
        add(name, new JsonString(value));
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder add(String name, BigInteger value) {
        add(name, new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder add(String name, BigDecimal value) {
        add(name, new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder add(String name, int value) {
        add(name, new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder add(String name, long value) {
        add(name, new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder add(String name, double value) {
        add(name, new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder add(String name, boolean value) {
        add(name, value ? JsonValue.TRUE : JsonValue.FALSE);
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder addNull(String name) {
        add(name, JsonValue.NULL);
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder add(String name, javax.json.JsonObjectBuilder builder) {
        jsonObject.put(name, builder.build());
        return this;
    }

    @Override
    public javax.json.JsonObjectBuilder add(String name, JsonArrayBuilder builder) {
        jsonObject.put(name, builder.build());
        return this;
    }

    @Override
    public JsonObject build() {
        return jsonObject;
    }
}
