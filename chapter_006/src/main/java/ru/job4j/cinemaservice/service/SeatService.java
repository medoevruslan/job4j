package ru.job4j.cinemaservice.service;

import ru.job4j.cinemaservice.model.Account;
import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;
import ru.job4j.cinemaservice.persistent.SeatDAO;
import ru.job4j.cinemaservice.persistent.dbasedao.SeatManager;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for working with Seat DAO
 */
public class SeatService implements SeatDAO {
    private static final SeatService INSTANCE = new SeatService();
    private final SeatManager manager = new SeatManager();

    private SeatService() { }

    public static SeatDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Seat model) {
        return this.manager.add(model);
    }

    @Override
    public boolean update(Seat model) {
        return this.manager.update(model);
    }

    @Override
    public boolean remove(Seat model) {
        return this.manager.remove(model);
    }

    @Override
    public Optional<Seat> findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Seat> findAll() {
        return this.manager.findAll();
    }

    @Override
    public Optional<Account> findAccountBySeat(Seat seat) {
        return this.manager.findAccountBySeat(seat);
    }

    @Override
    public boolean addRow(int seatsQuant, int price, Hall hall) {
        return this.manager.addRow(seatsQuant, price, hall);
    }

}
