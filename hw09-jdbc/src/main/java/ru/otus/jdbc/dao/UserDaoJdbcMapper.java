package ru.otus.jdbc.dao;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.mapper.JdbcMapper;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.util.Optional;

public class UserDaoJdbcMapper implements UserDao {

    private final SessionManagerJdbc sessionManagerJdbc;
    private final DbExecutorImpl<User> dbExecutor;
    private final JdbcMapper<User> jdbcMapperUser;

    public UserDaoJdbcMapper(SessionManagerJdbc sessionManager, DbExecutorImpl<User> dbExecutor, JdbcMapper<User> jdbcMapperUser) {
        this.sessionManagerJdbc = sessionManager;
        this.dbExecutor = dbExecutor;
        this.jdbcMapperUser = jdbcMapperUser;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public long insertUser(User user) {
        jdbcMapperUser.insert(user);
        return dbExecutor.executeInsert(getSessionManager().getCurrentSession()., jdbcMapperUser.);
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManagerJdbc;
    }
}
