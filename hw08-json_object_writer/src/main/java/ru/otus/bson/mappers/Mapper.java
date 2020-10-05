package ru.otus.bson.mappers;

public interface Mapper<T> {

    T toValue(Object object);
}
