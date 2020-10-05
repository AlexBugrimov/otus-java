package ru.otus.bson;

public interface Mapper<T> {

    T toValue(Object object);
}
