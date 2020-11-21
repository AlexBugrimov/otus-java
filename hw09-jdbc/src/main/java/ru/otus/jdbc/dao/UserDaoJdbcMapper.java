package ru.otus.jdbc.dao;

import ru.otus.db.model.User;
import ru.otus.jdbc.mapper.JdbcMapper;

public class UserDaoJdbcMapper extends DaoJdbcMapper<User> {

    public UserDaoJdbcMapper(JdbcMapper<User> jdbcMapper) {
        super(jdbcMapper);
    }
}
