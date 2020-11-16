package ru.otus.core.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet addressDataSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhoneDataSet> phones = new ArrayList<>();

    public User() {
    }

    public User(String name, AddressDataSet addressDataSet, List<PhoneDataSet> phones, String password) {
        this.name = name;
        this.addressDataSet = addressDataSet;
        this.phones = phones;
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
        return addressDataSet;
    }

    public User setAddressDataSet(AddressDataSet addressDataSet) {
        this.addressDataSet = addressDataSet;
        return this;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public User setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
        return this;
    }

    public User setPhones(String... phones) {
        setPhones(Arrays.stream(phones).map(phone -> new PhoneDataSet(phone, this)).collect(Collectors.toList()));
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("address=" + addressDataSet)
                .add("phones=" + phones)
                .toString();
    }
}
