package ru.otus.db.model;

import ru.otus.jdbc.mapper.Id;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class User {

    @Id
    private long id;
    private String name;

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
