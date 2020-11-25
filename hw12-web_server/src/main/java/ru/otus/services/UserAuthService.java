package ru.otus.services;

import ru.otus.db.service.UserService;

public interface UserAuthService {

    boolean authenticate(String login, String password);

    UserService getUserService();
}
