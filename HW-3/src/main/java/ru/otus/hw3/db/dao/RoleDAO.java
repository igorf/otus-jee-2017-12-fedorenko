package ru.otus.hw3.db.dao;

import ru.otus.hw3.db.model.Role;
import ru.otus.hw3.db.transport.EntityHelper;

import javax.persistence.Query;
import java.util.List;

public class RoleDAO {
    public List<Role> list() {
        return EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("from Role");
            List<Role> result = q.getResultList();
            return result;
        });
    }
}
