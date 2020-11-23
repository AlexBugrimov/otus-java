package ru.otus.db.model;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    public User() {
    }

    public User(String name, AddressDataSet address, String password) {
        this.name = name;
        this.address = address;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public AddressDataSet getAddressDataSet() {
        return address;
    }

    public User setAddressDataSet(AddressDataSet addressDataSet) {
        this.address = addressDataSet;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("address=" + address)
                .toString();
    }
}
