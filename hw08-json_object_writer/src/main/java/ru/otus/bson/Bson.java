package ru.otus.bson;

public class Bson {

    private final Adapter adapter;

    public Bson() {
        this(new ClassAdapter());
    }

    public Bson(Adapter adapter) {
        this.adapter = adapter;
    }

    public String toJson(Object object) {
        return adapter.transform(object.getClass());
    }
}
