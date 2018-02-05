package ru.otus.hw5.db.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique=true, nullable = false)
    String role;
}
