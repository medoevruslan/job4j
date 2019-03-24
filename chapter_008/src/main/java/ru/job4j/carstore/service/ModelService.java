package ru.job4j.carstore.service;

import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.dao.implementation.Manager;
import ru.job4j.carstore.entity.Model;

import java.util.List;

public class ModelService implements DAO<Model> {
    private static final DAO INSTANCE = new ModelService();
    private final Manager<Model> manager = new Manager<>(Model.class);

    private ModelService() { }

    public static DAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Model entity) {
        return this.manager.add(entity);
    }

    @Override
    public void update(Model entity) {
        this.manager.update(entity);
    }

    @Override
    public void delete(Model entity) {
        this.manager.delete(entity);
    }

    @Override
    public Model findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Model> findAll() {
        return this.manager.findAll();
    }

    @Override
    public void deleteAll() {
        this.manager.deleteAll();
    }
}
