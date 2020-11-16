package ru.otus.core.service;

import java.util.List;
import java.util.Optional;

public interface DbService<T> {

    long save(T t);

    Optional<T> getById(long id);

    Optional<T> findByLogin(String login);

    List<T> findAll();
}
