package ru.otus.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.util.security.Password;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.User;

import java.util.Optional;

public class InDbLoginServiceImpl extends AbstractLoginService {

    private final Dao<User> userDao;

    public InDbLoginServiceImpl(Dao<User> userDao) {
        this.userDao = userDao;
    }


    @Override
    protected String[] loadRoleInfo(UserPrincipal userPrincipal) {
        return new String[]{"user"};
    }

    @Override
    protected UserPrincipal loadUserInfo(String login) {
        System.out.printf("InMemoryLoginService#loadUserInfo(%s)%n", login);
        Optional<User> dbUser = userDao.findByLogin(login);
        return dbUser.map(u -> new UserPrincipal(u.getName(), new Password(u.getPassword()))).orElse(null);
    }
}
