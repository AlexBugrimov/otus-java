package ru.otus.core.model;

import ru.otus.jdbc.mapper.Id;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Account {

    @Id
    private long no;
    private String type;
    private BigDecimal rest;

    public Account() {
    }

    public Account(String type, BigDecimal rest) {
        this.type = type;
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getRest() {
        return rest;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("no=" + no)
                .add("type='" + type + "'")
                .add("rest=" + rest)
                .toString();
    }
}
