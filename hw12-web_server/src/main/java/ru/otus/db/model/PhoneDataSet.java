package ru.otus.db.model;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "phones")
public class PhoneDataSet extends BaseEntity {

    @Column(name = "number")
    private String number;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number, User user) {
        this.number = number;
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public PhoneDataSet setNumber(String number) {
        this.number = number;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ").add("'" + number + "'").toString();
    }
}
