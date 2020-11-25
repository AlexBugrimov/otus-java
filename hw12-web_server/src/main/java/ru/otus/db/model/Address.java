package ru.otus.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.StringJoiner;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(name = "street")
    private String street;

    public Address() {
    }

    public Address(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]").add("street='" + street + "'").toString();
    }
}
