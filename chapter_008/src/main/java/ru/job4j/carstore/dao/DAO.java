package ru.job4j.carstore.dao;

import java.util.List;

/**
 * DAO.
 * @param <E> Entity.
 */
public interface DAO<E> {

    int add(E entity);
    void update(E entity);
    void delete(E entity);
    E findById(int id);
    List<E> findAll();
    void deleteAll();

}
