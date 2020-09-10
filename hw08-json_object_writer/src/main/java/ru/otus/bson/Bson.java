package ru.otus.bson;

public class Bson {

    private final static Adapter DEFAULT_ADAPTER = new ClassAdapter();
    private final Adapter adapter;

    public Bson() {
        this(DEFAULT_ADAPTER);
    }

    public Bson(Adapter adapter) {
        this.adapter = adapter;
    }

    public String toJson(Object object) {
        return adapter.transform(object);
    }
}
