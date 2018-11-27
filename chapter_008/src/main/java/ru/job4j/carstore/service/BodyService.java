package ru.job4j.carstore.service;

import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.dao.implementation.Manager;
import ru.job4j.carstore.entity.Body;

import java.util.List;

/**
 * Implementation of Body DAO.
 */
public class BodyService implements DAO<Body> {
    private static final DAO INSTANCE = new BodyService();
    private final Manager<Body> manager = new Manager<>(Body.class);

    public static DAO getInstance() {
        return INSTANCE;
    }

    private BodyService() { }

    @Override
    public int add(Body entity) {
        return this.manager.add(entity);
    }

    @Override
    public void update(Body entity) {
        this.manager.update(entity);
    }

    @Override
    public void delete(Body entity) {
        this.manager.delete(entity);
    }

    @Override
    public Body findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Body> findAll() {
        return this.manager.findAll();
    }

    @Override
    public void deleteAll() {
        this.manager.deleteAll();
    }
}
