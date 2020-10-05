package ru.otus.bson;

import javax.json.JsonValue;

public class Bson {

    private static final Handler<ClassInfo> CLASS_HANDLER = new ClassHandler();
    private final Mapper<JsonValue> mapper;

    public Bson() {
        this(CLASS_HANDLER);
    }

    public Bson(Handler<ClassInfo> handler) {
        this(new ObjectMapper(handler));
    }

    public Bson(Mapper<JsonValue> mapper) {
        this.mapper = mapper;
    }

    public String toJson(Object object) {
        return mapper.toValue(object).toString();
    }
}
