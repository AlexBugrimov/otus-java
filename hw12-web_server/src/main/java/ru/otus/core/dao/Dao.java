package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(long id);

    long insert(T t);

    Optional<T> findByLogin(String login);

    List<T> findAll();

    SessionManager getSessionManager();
}
