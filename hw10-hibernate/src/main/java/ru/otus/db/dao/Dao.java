package ru.otus.db.dao;

import ru.otus.db.sessionmanager.SessionManager;

import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(long id);

    long insert(T t);

    void update(T t);

    void insertOrUpdate(T t);

    SessionManager getSessionManager();
}
