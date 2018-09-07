package ru.job4j.servlets;

import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService implements Store<User> {
    private static final ValidateService INSTANCE = new ValidateService();
    private ValidateService() { }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    private final Store store = DBStore.getInstance();

    @Override
    public boolean add(User user) {
        return this.store.add(user);
    }

    @Override
    public boolean update(User user, String name, String email, String login, String password, String role) {
        boolean rst = false;
        if (user != null) {
            rst = this.store.update(user, name, email, login, password, role);
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
}
