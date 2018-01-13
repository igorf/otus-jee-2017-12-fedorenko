package ru.otus.hw2.server.services;

import ru.otus.hw2.db.model.Employee;

import java.util.List;
import java.util.logging.Logger;

public class ImportService {
    private static ImportService instance = null;
    private static final String DELIMITER = ";";
    private static final Integer EMP_FIELDS = 11;
    private static final Logger logger = Logger.getLogger(ImportService.class.getName());

    public static ImportService getInstance() {
        if (instance == null) {
            instance = new ImportService();
        }
        return instance;
    }

    private ImportService() {
        super();
    }

    public void importData(List<String> incoming) {
        incoming.forEach(this::saveEmployeeByString);
    }

    private void saveEmployeeByString(String incoming) {
        Employee employee = createEmployeeByString(incoming);
        if (employee != null) {
            EmployeeService.getInstance().save(employee);
        }
    }

    private Employee createEmployeeByString(String incoming) {
        Employee result = null;
        try {
            String[] parts = incoming.split(DELIMITER);
            if (parts.length == EMP_FIELDS) {
                result = new Employee();
                result.setCity(parts[0]);
                result.setEmail(parts[1]);
                result.setFirstname(parts[2]);
                result.setLastname(parts[3]);
                result.setLogin(parts[4]);
                result.setPassword(parts[5]);
                result.setPhone(parts[6]);
                result.setPosition(PositionService.getInstance().positionByID(Long.valueOf(parts[7])));
                result.setUnit(parts[8]);
                result.setRole(RoleService.getInstance().roleByID(Long.valueOf(parts[9])));
                result.setSalary(Integer.valueOf(parts[10]));
            }
        } catch (Exception ex) {
            logger.severe(ex.getLocalizedMessage());
        }
        return result;
    }
}

