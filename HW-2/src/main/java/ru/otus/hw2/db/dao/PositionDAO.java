package ru.otus.hw2.db.dao;

import ru.otus.hw2.db.model.Position;
import ru.otus.hw2.db.transport.EntityHelper;

import javax.persistence.Query;
import java.util.List;

public class PositionDAO {
    public List<Position> list() {
        return EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("from Position");
            List<Position> result = q.getResultList();
            return result;
        });
    }
}
