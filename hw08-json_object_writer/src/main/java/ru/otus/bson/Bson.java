package ru.otus.bson;

import ru.otus.bson.handlers.ClassHandler;
import ru.otus.bson.handlers.Handler;
import ru.otus.bson.info.ClassInfo;
import ru.otus.bson.mappers.Mapper;
import ru.otus.bson.mappers.ObjectMapper;

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
