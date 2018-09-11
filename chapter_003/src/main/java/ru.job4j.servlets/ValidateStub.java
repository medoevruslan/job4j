package ru.job4j.servlets;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * Class developed for make a test
 */
public class ValidateStub implements Validate {
    private ArrayList<User> users = new ArrayList<>();
    private int id = 0;

    @Override
    public boolean add(User user) {
        boolean rst = true;
        for (User usr : this.users) {
            if (user.getLogin().equals(usr.getLogin())){
                rst = false;
                break;
            }
        }
        if (rst) {
            user.setId(id++);
            this.users.add(user);
        }
        return rst;
    }

    @Override
    public boolean update(User user, String name, String email, String login, String password, String role) {
        for (int i = 0; i < this.users.size(); i++) {
            int id = user.getId();
            if (id == users.get(i).getId()) {
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(new Role(role));
                users.set(i, user);
                break;
            }
        }
        return true;
    }

    @Override
    public boolean delete(User user) {
        this.users.remove(user);
        return true;
    }

    @Override
    public User findById(int id) {
        User user = null;
        for (User usr : this.users) {
            if (usr.getId() == id) {
                user = usr;
                break;
            }
        }
        return user;
    }

    @Override
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
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public void resetList() {
        this.users.clear();
        this.id = 0;
    }
}
