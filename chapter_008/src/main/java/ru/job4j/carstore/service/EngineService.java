package ru.job4j.carstore.service;

import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.dao.implementation.Manager;
import ru.job4j.carstore.entity.Engine;

import java.util.List;

/**
 * Implementation of Engine DAO.
 */
public class EngineService implements DAO<Engine> {
    private static final DAO INSTANCE = new EngineService();
    private final Manager<Engine> manager = new Manager<>(Engine.class);

    private EngineService() { }

    public static DAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Engine entity) {
        return this.manager.add(entity);
    }

    @Override
    public void update(Engine entity) {
        this.manager.update(entity);
    }

    @Override
    public void delete(Engine entity) {
        this.manager.delete(entity);
    }

    @Override
    public Engine findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Engine> findAll() {
        return this.manager.findAll();
    }

    @Override
    public void deleteAll() {
        this.manager.deleteAll();
    }
}
