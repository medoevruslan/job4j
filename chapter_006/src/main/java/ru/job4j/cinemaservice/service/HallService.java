package ru.job4j.cinemaservice.service;

import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;
import ru.job4j.cinemaservice.persistent.HallDAO;
import ru.job4j.cinemaservice.persistent.dbasedao.HallManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for working with Hall DAO
 */
public class HallService implements HallDAO {
    private static final HallService INSTANCE = new HallService();
    private final HallManager manager = new HallManager();

    private HallService() { }

    public static HallDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Hall model) {
        return this.manager.add(model);
    }

    @Override
    public boolean update(Hall model) {
        return this.manager.update(model);
    }

    @Override
    public boolean remove(Hall model) {
        return this.manager.remove(model);
    }

    @Override
    public Optional<Hall> findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Hall> findAll() {
        return this.manager.findAll();
    }

    @Override
    public HashMap<Integer, ArrayList<Seat>> getSeatsByHall(Hall hall) {
        return this.manager.getSeatsByHall(hall);
    }

}
