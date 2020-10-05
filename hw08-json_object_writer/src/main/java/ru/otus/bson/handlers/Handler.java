package ru.otus.bson.handlers;

public interface Handler<T> {

    T handle(Object object);
}
