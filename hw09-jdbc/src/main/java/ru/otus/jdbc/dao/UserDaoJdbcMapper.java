package ru.otus.jdbc.dao;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.mapper.JdbcMapper;

import java.util.Optional;

public class UserDaoJdbcMapper implements UserDao {

    private final JdbcMapper<User> jdbcMapper;

    public UserDaoJdbcMapper(JdbcMapper<User> jdbcMapper) {
        this.jdbcMapper = jdbcMapper;
    }

    @Override
    public Optional<User> findById(long id) {
        return jdbcMapper.findById(id);
    }

    @Override
    public long insertUser(User user) {
        return jdbcMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        jdbcMapper.update(user);
    }

    @Override
    public void insertOrUpdate(User user) {
        jdbcMapper.insertOrUpdate(user);
    }

    @Override
    public SessionManager getSessionManager() {
        return jdbcMapper.getSessionManager();
    }
}
