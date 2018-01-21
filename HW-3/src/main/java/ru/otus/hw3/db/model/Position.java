package ru.otus.hw3.db.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true, nullable = false)
    String position;
}
