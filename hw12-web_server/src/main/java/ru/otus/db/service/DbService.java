package ru.otus.db.service;

import java.util.List;
import java.util.Optional;

public interface DbService<T> {

    void save(T t);

    Optional<T> getById(long id);

    List<T> findAll();
}
