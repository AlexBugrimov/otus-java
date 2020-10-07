package ru.otus.bson.jsonTypes;

import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonArrayBuilder implements javax.json.JsonArrayBuilder {

    private final JsonArray jsonArray = new JsonArray();

    @Override
    public javax.json.JsonArrayBuilder add(JsonValue value) {
        jsonArray.add(value);
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder add(String value) {
        jsonArray.add(new JsonString(value));
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder add(BigDecimal value) {
        jsonArray.add(new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder add(BigInteger value) {
        jsonArray.add(new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder add(int value) {
        jsonArray.add(new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder add(long value) {
        jsonArray.add(new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder add(double value) {
        jsonArray.add(new JsonNumber(value));
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder add(boolean value) {
        jsonArray.add(value ? JsonValue.TRUE : JsonValue.FALSE);
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder addNull() {
        jsonArray.add(JsonValue.NULL);
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder add(JsonObjectBuilder builder) {
        jsonArray.add(builder.build());
        return this;
    }

    @Override
    public javax.json.JsonArrayBuilder add(javax.json.JsonArrayBuilder builder) {
        jsonArray.addAll(builder.build());
        return this;
    }

    @Override
    public JsonArray build() {
        return jsonArray;
    }
}
