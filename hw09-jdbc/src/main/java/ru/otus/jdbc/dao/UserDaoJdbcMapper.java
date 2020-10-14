package ru.otus.jdbc.dao;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.mapper.EntityClassMetaDataHandler;
import ru.otus.jdbc.mapper.JdbcMapper;
import ru.otus.jdbc.mapper.JdbcMapperMetaData;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.util.Optional;

public class UserDaoJdbcMapper implements UserDao {

    private final JdbcMapper<User> jdbcMapperUser;
    private final SessionManagerJdbc sessionManager;

    public UserDaoJdbcMapper(SessionManagerJdbc sessionManager, DbExecutor<User> executor) {
        this.jdbcMapperUser = new JdbcMapperMetaData(sessionManager, executor, EntityClassMetaDataHandler.of(User.class));
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.of(jdbcMapperUser.findById(id, User.class));
    }

    @Override
    public long insertUser(User user) {
        return jdbcMapperUser.insert(user);
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
