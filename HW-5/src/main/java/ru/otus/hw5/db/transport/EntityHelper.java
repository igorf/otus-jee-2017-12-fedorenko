package ru.otus.hw5.db.transport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.function.Function;
import java.util.logging.Logger;

public class EntityHelper {

    private final static Logger log = Logger.getLogger(EntityHelper.class.getName());

    public static <R> R runWithManager(Function<EntityManager, R> function) {
        EntityManagerFactory managerFactory = JPAHelper.getEntityManagerFactory();
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        R result = null;

        try {
            transaction.begin();
            result = function.apply(em);
            transaction.commit();
        } catch (Exception ex) {
            log.severe(ex.getLocalizedMessage());
            transaction.rollback();
        } finally {
            em.close();
        }

        return result;
    }
}
