package ru.otus.hw2.db.dao;

import ru.otus.hw2.db.model.Employee;
import ru.otus.hw2.db.transport.EntityHelper;

import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class EmployeeDAO {
    private final static String MAX_SALARY_EMPLOYEE_PROC = "max_salary_employee";

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
                entityManager.persist(employee);
            } catch (Exception ex) {
                return false;
            }
            return true;
        });
    }

    public String findMVE() {
        return EntityHelper.runWithManager(entityManager -> {
            StoredProcedureQuery q = entityManager.createStoredProcedureQuery(MAX_SALARY_EMPLOYEE_PROC);
            if (q.execute()) {
                return (String) q.getSingleResult();
            }
            return null;
        });
    }
}
