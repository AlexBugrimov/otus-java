package ru.otus.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = true)
public class AddressDataSet extends BaseEntity {

    @Column(name = "street")
    private String street;
}

