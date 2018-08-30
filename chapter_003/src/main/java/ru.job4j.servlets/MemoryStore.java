package ru.job4j.servlets;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStore implements Store {
    private List<User> users = new CopyOnWriteArrayList<>();
    private static final MemoryStore INSTANCE = new MemoryStore();
    private MemoryStore() { }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        this.users.add(user);
        return true;
    }

    @Override
    public boolean update(User user, String name, String email, String login) {
        for (int i = 0; i < this.users.size(); i++) {
            if (user.getId() == users.get(i).getId()) {
                user.setName(name);
                user.setEmail(email);
                user.setLogin(login);
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
    public List<User> findAll() {
        return this.users;
    }
}
