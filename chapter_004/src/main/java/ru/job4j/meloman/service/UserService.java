package ru.job4j.meloman.service;

import ru.job4j.meloman.dao.FactoryDao;
import ru.job4j.meloman.dao.UserDAO;
import ru.job4j.meloman.dao.implementation.UserManager;
import ru.job4j.meloman.entity.Address;
import ru.job4j.meloman.entity.MusicType;
import ru.job4j.meloman.entity.Role;
import ru.job4j.meloman.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service layer for working with UserDAO.
 */
public class UserService implements UserDAO {
    private static final UserService INSTANCE = new UserService();

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    private UserService() { }

    private final UserManager manager = FactoryDao.getInstance().getUserDao();

    @Override
    public boolean add(User model) {
        return this.manager.add(model);
    }

    @Override
    public boolean remove(User model) {
        return this.manager.remove(model);
    }

    @Override
    public boolean update(User data) {
        return this.manager.update(data);
    }

    @Override
    public User findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.manager.findAll());
    }

    @Override
    public boolean addFullUser(User user) {
        return this.manager.addFullUser(user);
    }

    @Override
    public Role getRole(User user) {
        return this.manager.getRole(user);
    }

    @Override
    public Address getAddress(User user) {
        return this.manager.getAddress(user);
    }

    @Override
    public Set<MusicType> getMusicType(User user) {
        return new HashSet<>(this.manager.getMusicType(user));
    }

    @Override
    public List<User> getAllEntities() {
        return new ArrayList<>(this.manager.getAllEntities());
    }
}

