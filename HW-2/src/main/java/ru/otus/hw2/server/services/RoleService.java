package ru.otus.hw2.server.services;


import ru.otus.hw2.db.dao.RoleDAO;
import ru.otus.hw2.db.model.Role;

import java.util.List;

public class RoleService {
    private static RoleService instance = null;
    private static final RoleDAO roleDAO = new RoleDAO();
    private static List<Role> roles = roleDAO.list();

    public static RoleService getInstance() {
        if (instance == null) {
            instance = new RoleService();
        }
        return instance;
    }

    private RoleService() {
        super();
    }

    public List<Role> list() {
        return roles;
    }

    public Role roleByID(long id) {
        for (Role role: list()) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }
}

