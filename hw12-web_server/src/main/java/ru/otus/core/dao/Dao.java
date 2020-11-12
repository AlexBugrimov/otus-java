package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(long id);

    long insert(T t);

    void update(T t);

    void insertOrUpdate(T t);

    Optional<T> findByLogin(String login);

    SessionManager getSessionManager();

    Optional<T> findRandom();
}
