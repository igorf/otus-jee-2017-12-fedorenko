package ru.otus.hw5.server.services;


import ru.otus.hw5.db.dao.RoleDAO;
import ru.otus.hw5.db.model.Role;

import java.util.List;
import java.util.logging.Logger;

public class RoleService {
    private static RoleService instance = null;
    private static final RoleDAO roleDAO = new RoleDAO();
    private static List<Role> roles = roleDAO.list();
    private static final Logger logger = Logger.getLogger(RoleService.class.getName());

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

    public void remove(long roleId) {
        roleDAO.removeByID(roleId);
        roles = roleDAO.list();
    }

    public void save(Role role) {
        try {
            roleDAO.save(role);
        } catch (Exception ex) {
            logger.severe(ex.getLocalizedMessage());
        }
        roles = roleDAO.list();
    }
}

