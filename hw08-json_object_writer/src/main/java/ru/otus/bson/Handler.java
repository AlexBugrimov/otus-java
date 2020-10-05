package ru.otus.bson;

public interface Handler<T> {

    T handle(Object object);
}
