package ru.job4j.servlets;

import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public interface Validate extends Store<User> {
    boolean add(User user);
    boolean update(User user, String name, String email, String login, String password, String role);
    boolean delete(User user);
    User findById(int id);
    List<User> findAll();
    boolean isCorrect(String login, String password);
    void resetList();
}
