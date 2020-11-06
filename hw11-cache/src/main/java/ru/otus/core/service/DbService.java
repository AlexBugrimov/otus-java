package ru.otus.core.service;

import java.util.Optional;

public interface DbService<T, K> {

    K save(T t);

    Optional<T> getById(K id);
}
