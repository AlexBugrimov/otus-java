package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface Dao<T, K> {

    Optional<T> findById(K id);

    K insert(T t);

    void update(T t);

    void insertOrUpdate(T t);

    SessionManager getSessionManager();
}
