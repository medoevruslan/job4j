package ru.job4j.carstore.service;

import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.dao.implementation.Manager;
import ru.job4j.carstore.entity.Car;

import java.util.List;

/**
 * Implementation of Car DAO.
 */
public class CarService implements DAO<Car> {
    private static final DAO INSTANCE = new CarService();
    private final Manager<Car> manager = new Manager<>(Car.class);

    private CarService() { }

    public static DAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Car entity) {
        return this.manager.add(entity);
    }

    @Override
    public void update(Car entity) {
        this.manager.update(entity);
    }

    @Override
    public void delete(Car entity) {
        this.manager.delete(entity);
    }

    @Override
    public Car findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return this.manager.findAll();
    }

    @Override
    public void deleteAll() {
        this.manager.deleteAll();
    }
}
