package ru.job4j.servlets;

import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService implements Store {
    private static final ValidateService INSTANCE = new ValidateService();
    private ValidateService() { }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    private final MemoryStore store = MemoryStore.getInstance();

    @Override
    public boolean add(User user) {
        return this.store.add(user);
    }

    @Override
    public boolean update(User user, String name, String email, String login) {
        boolean rst = false;
        if (user != null) {
            rst = this.store.update(user, name, email, login);
        }
        return rst;
    }

    @Override
    public boolean delete(User user) {
        boolean rst = false;
        if (user != null) {
            rst = this.store.delete(user);
        }
        return rst;
    }

    @Override
    public User findById(int id) {
        return this.store.findById(id);
    }

    @Override
    public List<User> findAll() {
        return this.store.findAll();
    }
}
