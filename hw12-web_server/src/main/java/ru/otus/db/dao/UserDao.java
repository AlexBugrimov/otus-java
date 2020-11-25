package ru.otus.db.dao;

import ru.otus.db.model.User;
import ru.otus.db.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByLogin(String login);

    long insert(User user);

    SessionManager getSessionManager();
}
