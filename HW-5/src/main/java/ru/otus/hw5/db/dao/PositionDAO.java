package ru.otus.hw5.db.dao;

import ru.otus.hw5.db.model.Position;
import ru.otus.hw5.db.transport.EntityHelper;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class PositionDAO {
    private final static Logger logger = Logger.getLogger(Position.class.getName());

    public List<Position> list() {
        return EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("from Position");
            List<Position> result = q.getResultList();
            return result;
        });
    }

    public Position getByID(final long id) {
        return EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("from Position where id=:id")
                    .setParameter("id", id)
                    .setMaxResults(1);
            return (Position) q.getSingleResult();
        });
    }

    public void removeByID(final long id) {
        EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("delete from Position where id=:id")
                    .setParameter("id", id);
            return q.executeUpdate();
        });
    }

    public boolean save(final Position position) {
        return EntityHelper.runWithManager(entityManager -> {
            try {
                entityManager.merge(position);
            } catch (Exception ex) {
                logger.severe(ex.getMessage());
                return false;
            }
            return true;
        });
    }
}
