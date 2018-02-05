package ru.otus.hw5.db.dao;

import ru.otus.hw5.db.model.Employee;
import ru.otus.hw5.db.transport.EntityHelper;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class EmployeeDAO {

    private final static Logger logger = Logger.getLogger(Employee.class.getName());

    public List<Employee> list() {
        return EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("from Employee");
            List<Employee> result = q.getResultList();
            return result;
        });
    }

    public Employee getByID(final long id) {
        return EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("from Employee where id=:id")
                    .setParameter("id", id)
                    .setMaxResults(1);
            return (Employee) q.getSingleResult();
        });
    }

    public Employee getByLogin(final String login) {
        return EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("from Employee where login=:login")
                    .setParameter("login", login)
                    .setMaxResults(1);
            return (Employee) q.getSingleResult();
        });
    }

    public void removeByID(final long id) {
        EntityHelper.runWithManager(entityManager -> {
            Query q = entityManager.createQuery("delete from Employee where id=:id")
                    .setParameter("id", id);
            return q.executeUpdate();
        });
    }

    public boolean save(final Employee employee) {
        return EntityHelper.runWithManager(entityManager -> {
            try {
                entityManager.merge(employee);
            } catch (Exception ex) {
                logger.severe(ex.getMessage());
                return false;
            }
            return true;
        });
    }
}
