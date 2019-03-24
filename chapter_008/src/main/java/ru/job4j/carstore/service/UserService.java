package ru.job4j.carstore.service;

import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.dao.implementation.Manager;
import ru.job4j.carstore.entity.User;

import java.util.List;

public class UserService implements DAO<User> {
    private static final DAO INSTANCE = new UserService();
    private final Manager<User> manager = new Manager<>(User.class);

    private UserService() { }

    public static DAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(User entity) {
        return this.manager.add(entity);
    }

    @Override
    public void update(User entity) {
        this.manager.update(entity);
    }

    @Override
    public void delete(User entity) {
        this.manager.delete(entity);
    }

    @Override
    public User findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<User> findAll() {
        return this.manager.findAll();
    }

    @Override
    public void deleteAll() {
        this.manager.deleteAll();
    }
}
