package ru.job4j.servlets;

import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public interface Store<T> {
    boolean add(T t);
    boolean update(T t, String name, String email, String login, String password,
                   String country, String city, String role);
    boolean delete(T t);
    T findById(int id);
    List<T> findAll();
}
