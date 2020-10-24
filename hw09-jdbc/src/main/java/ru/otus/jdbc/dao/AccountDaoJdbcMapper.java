package ru.otus.jdbc.dao;

import ru.otus.core.model.Account;
import ru.otus.jdbc.mapper.JdbcMapper;

public class AccountDaoJdbcMapper extends DaoJdbcMapper<Account> {

    public AccountDaoJdbcMapper(JdbcMapper<Account> jdbcMapper) {
        super(jdbcMapper);
    }
}
