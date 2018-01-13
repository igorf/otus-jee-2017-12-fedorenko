package ru.otus.hw2.db.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @Column(nullable = false)
    String position;
    Integer salary;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    Role role;
}
