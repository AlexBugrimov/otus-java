package ru.otus.db.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.db.dao.UserDao;
import ru.otus.db.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var id = userDao.insert(user);
                sessionManager.commitSession();
                logger.info("created entity: {}", id);
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<User> getById(long id) {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> entityOptional = userDao.findById(id);

                logger.info("entity: {}", entityOptional.orElse(null));
                return entityOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> entityOptional = userDao.findByLogin(login);

                logger.info("entity: {}", entityOptional.orElse(null));
                return entityOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return userDao.findAll();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return new ArrayList<>();
        }
    }
}
