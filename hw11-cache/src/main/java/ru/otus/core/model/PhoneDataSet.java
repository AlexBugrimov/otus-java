package ru.otus.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = true)
public class PhoneDataSet extends BaseEntity {

    @Column(name = "number")
    private String number;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
