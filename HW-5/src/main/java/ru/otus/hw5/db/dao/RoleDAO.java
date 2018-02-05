package ru.otus.hw5.db.dao;

import ru.otus.hw5.db.model.Role;
import ru.otus.hw5.db.transport.EntityHelper;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class RoleDAO {
    private final static Logger logger = Logger.getLogger(Role.class.getName());

    public List<Role> list() {
        return EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("from Role");
            List<Role> result = q.getResultList();
            return result;
        });
    }

    public Role getByID(final long id) {
        return EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("from Role where id=:id")
                    .setParameter("id", id)
                    .setMaxResults(1);
            return (Role) q.getSingleResult();
        });
    }

    public void removeByID(final long id) {
        EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("delete from Role where id=:id")
                    .setParameter("id", id);
            return q.executeUpdate();
        });
    }

    public boolean save(final Role role) {
        return EntityHelper.runWithManager(entityManager -> {
            try {
                entityManager.merge(role);
            } catch (Exception ex) {
                logger.severe(ex.getMessage());
                return false;
            }
            return true;
        });
    }
}
