package ru.job4j.servlets;

import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService implements Validate {
    private static final Validate INSTANCE = new ValidateService();
    private ValidateService() { }

    public static Validate getInstance() {
        return INSTANCE;
    }

    private final Store store = DBStore.getInstance();

    @Override
    public boolean add(User user) {
        return this.store.add(user);
    }

    @Override
    public boolean update(User user, User newUser) {
        boolean rst = false;
        if (user != null) {
            rst = this.store.update(user, newUser);
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

    public boolean isCorrect(String login, String password) {
        boolean rst = false;
        for (User user : this.findAll()) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                rst = true;
                break;
            }
        }
        return  rst;
    }

    @Override
    public User findById(int id) {
        return (User) this.store.findById(id);
    }

    @Override
    public List<User> findAll() {
        return this.store.findAll();
    }

    @Override
    public void resetList() {
    }
}