package ru.otus.services;

import ru.otus.core.dao.Dao;
import ru.otus.core.model.User;

public class UserAuthServiceImpl implements UserAuthService {

    private final Dao<User> userDao;

    public UserAuthServiceImpl(Dao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return userDao.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
