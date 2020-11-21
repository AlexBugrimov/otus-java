package ru.otus.db.service;

import ru.otus.db.model.User;

import java.util.Optional;

public interface UserService extends DbService<User> {

    Optional<User> findByLogin(String login);
}
