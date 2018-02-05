package ru.otus.hw5.server.services;

import ru.otus.hw5.db.dao.PositionDAO;
import ru.otus.hw5.db.model.Position;

import java.util.List;
import java.util.logging.Logger;

public class PositionService {
    private static PositionService instance = null;
    private static final PositionDAO positionDAO = new PositionDAO();
    private static List<Position> positions = positionDAO.list();
    private static final Logger logger = Logger.getLogger(PositionService.class.getName());

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

    public void remove(long positionID) {
        positionDAO.removeByID(positionID);
        positions = positionDAO.list();
    }

    public void save(Position position) {
        try {
            positionDAO.save(position);
        } catch (Exception ex) {
            logger.severe(ex.getLocalizedMessage());
        }
        positions = positionDAO.list();
    }
}

