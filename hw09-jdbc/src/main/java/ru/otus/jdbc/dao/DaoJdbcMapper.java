package ru.otus.jdbc.dao;

import ru.otus.core.dao.Dao;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.mapper.JdbcMapper;

import java.util.Optional;

public abstract class DaoJdbcMapper<T> implements Dao<T> {

    private final JdbcMapper<T> jdbcMapper;

    public DaoJdbcMapper(JdbcMapper<T> jdbcMapper) {
        this.jdbcMapper = jdbcMapper;
    }

    @Override
    public Optional<T> findById(long id) {
        return jdbcMapper.findById(id);
    }

    @Override
    public long insert(T t) {
        return jdbcMapper.insert(t);
    }

    @Override
    public void update(T t) {
        jdbcMapper.update(t);
    }

    @Override
    public void insertOrUpdate(T t) {
        jdbcMapper.insertOrUpdate(t);
    }

    @Override
    public SessionManager getSessionManager() {
        return jdbcMapper.getSessionManager();
    }
}
