package ru.otus.services;

import ru.otus.core.dao.Dao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbService;
import ru.otus.core.service.DbServiceImpl;

public class UserAuthServiceImpl implements UserAuthService {

    private final DbService<User> dbService;

    public UserAuthServiceImpl(Dao<User> userDao) {
        this.dbService = new DbServiceImpl<>(userDao);
    }

    @Override
    public boolean authenticate(String login, String password) {
        return dbService.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
