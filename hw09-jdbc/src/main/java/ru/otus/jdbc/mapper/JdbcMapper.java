package ru.otus.jdbc.mapper;

import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

/**
 * Сохраняет объект в базу, читает объект из базы
 * @param <T>
 */
public interface JdbcMapper<T> {

    long insert(T objectData);

    void update(T objectData);

    void insertOrUpdate(T objectData);

    Optional<T> findById(Object id);

    SessionManager getSessionManager();
}
