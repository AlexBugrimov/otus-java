package ru.otus.bson;

import ru.otus.bson.mappers.Mapper;
import ru.otus.bson.mappers.ObjectMapper;

import javax.json.JsonValue;

import static ru.otus.bson.utils.Predicates.isNull;

public class Bson {

    private final Mapper<JsonValue> mapper;

    public Bson() {
        this(new ObjectMapper());
    }

    public Bson(Mapper<JsonValue> mapper) {
        this.mapper = mapper;
    }

    public String toJson(Object object) {
        final JsonValue jsonValue = mapper.toValue(object);
        return isNull.test(jsonValue) ? "{}" : jsonValue.toString();
    }
}
