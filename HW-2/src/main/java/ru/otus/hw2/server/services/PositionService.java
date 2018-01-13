package ru.otus.hw2.server.services;

import ru.otus.hw2.db.dao.PositionDAO;
import ru.otus.hw2.db.model.Position;

import java.util.List;

public class PositionService {
    private static PositionService instance = null;
    private static final PositionDAO positionDAO = new PositionDAO();
    private static List<Position> positions = positionDAO.list();

    public static PositionService getInstance() {
        if (instance == null) {
            instance = new PositionService();
        }
        return instance;
    }

    private PositionService() {
        super();
    }

    public List<Position> list() {
        return positions;
    }

    public Position positionByID(long id) {
        for (Position role: list()) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }
}

