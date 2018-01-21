package ru.otus.hw3.db.model;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true, nullable = false)
    String login;
    String password;
    @Column(nullable = false)
    String firstname;
    @Column(nullable = false)
    String lastname;
    @Column(unique=true, nullable = false)
    String email;
    @Column(nullable = false)
    String phone;
    @Column(nullable = false)
    String unit;
    @Column(nullable = false)
    String city;
    Integer salary;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    Role role;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = true)
    Position position;
}
