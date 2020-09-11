package ru.otus.bson;

public interface JsonType {

    boolean isTypeWith(Attributes attributes);

    <T> Class<?> getClass(Class<T> type);
}
