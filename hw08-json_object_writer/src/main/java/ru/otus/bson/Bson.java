package ru.otus.bson;

public class Bson {

    private final static Convertor DEFAULT_CONVERTOR = new ClassConvertor();
    private final Convertor convertor;

    public Bson() {
        this(DEFAULT_CONVERTOR);
    }

    public Bson(Convertor convertor) {
        this.convertor = convertor;
    }

    public String toJson(Object object) {
        return convertor.convert(object);
    }
}
