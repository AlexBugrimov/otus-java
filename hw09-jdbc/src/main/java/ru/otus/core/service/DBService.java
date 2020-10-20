package ru.otus.core.service;

import java.util.Optional;

public interface DBService<T> {

    long save(T t);

    Optional<T> getById(long id);
}
