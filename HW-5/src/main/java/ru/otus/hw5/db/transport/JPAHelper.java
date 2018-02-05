package ru.otus.hw5.db.transport;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHelper {
    private static final String PERSISTENCE_UNIT_NAME = "jpa";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
