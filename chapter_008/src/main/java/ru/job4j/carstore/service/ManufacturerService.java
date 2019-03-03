package ru.job4j.carstore.service;

import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.dao.implementation.Manager;
import ru.job4j.carstore.entity.Manufacturer;

import java.util.List;

/**
 * Implementation of Manufacturer DAO.
 */
public class ManufacturerService implements DAO<Manufacturer> {
    private static final DAO INSTANCE = new ManufacturerService();
    private final Manager<Manufacturer> manager = new Manager<>(Manufacturer.class);

    private ManufacturerService() { }

    public static DAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Manufacturer entity) {
        return this.manager.add(entity);
    }

    @Override
    public void update(Manufacturer entity) {
        this.manager.update(entity);
    }

    @Override
    public void delete(Manufacturer entity) {
        this.manager.delete(entity);
    }

    @Override
    public Manufacturer findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manager.findAll();
    }

    @Override
    public void deleteAll() {
        this.manager.deleteAll();
    }

}
