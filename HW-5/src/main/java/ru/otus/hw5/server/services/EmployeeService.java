package ru.otus.hw5.server.services;


import ru.otus.hw5.db.dao.EmployeeDAO;
import ru.otus.hw5.db.model.Employee;

import java.util.List;
import java.util.logging.Logger;

public class EmployeeService {
    private static EmployeeService instance = null;
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final Logger logger = Logger.getLogger(EmployeeService.class.getName());

    public static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    private EmployeeService() {
        super();
    }

    public List<Employee> list() {
        return employeeDAO.list();
    }

    public void remove(long employeeID) {
        employeeDAO.removeByID(employeeID);
    }

    public Employee get(long employeeID) {
        return employeeDAO.getByID(employeeID);
    }

    public void save(Employee employee) {
        try {
            employeeDAO.save(employee);
        } catch (Exception ex) {
            logger.severe(ex.getLocalizedMessage());
        }
    }

    public Employee findByLoginAndPassword(String login, String password) {
        //TODO hash password
        Employee emp = employeeDAO.getByLogin(login);
        if (emp != null && emp.getPassword().equals(password)) {
            return emp;
        }
        return null;
    }
}

