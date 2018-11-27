package ru.job4j.carstore.service;

import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.dao.implementation.Manager;
import ru.job4j.carstore.entity.Transmission;

import java.util.List;

/**
 * Implementation of Transmission DAO.
 */
public class TransmissionService implements DAO<Transmission> {
    private static final DAO INSTANCE = new TransmissionService();
    private final Manager<Transmission> manager = new Manager<>(Transmission.class);

    private TransmissionService() { }

    public static DAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Transmission entity) {
        return this.manager.add(entity);
    }

    @Override
    public void update(Transmission entity) {
        this.manager.update(entity);
    }

    @Override
    public void delete(Transmission entity) {
        this.manager.delete(entity);
    }

    @Override
    public Transmission findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Transmission> findAll() {
        return this.manager.findAll();
    }

    @Override
    public void deleteAll() {
        this.manager.deleteAll();
    }
}
