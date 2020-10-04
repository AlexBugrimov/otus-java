package ru.otus.bson;

public class Bson {

    private static final Handler CLASS_HANDLER = new ClassHandler();
    private final Mapper mapper;

    public Bson() {
        this(CLASS_HANDLER);
    }

    public Bson(Handler handler) {
        this(new ObjectMapper(handler));
    }

    public Bson(Mapper mapper) {
        this.mapper = mapper;
    }

    public String toJson(Object object) {
        return mapper.toJsonValue(object).toString();
    }
}
