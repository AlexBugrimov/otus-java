package ru.otus.db.sessionmanager;

import java.sql.Connection;

public interface DatabaseSession {

    Connection getConnection();
}
