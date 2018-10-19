package ru.job4j.meloman.dao;

import ru.job4j.meloman.entity.Entity;
import java.util.List;

public interface DAO<T extends Entity> {

    boolean add(T model);
    boolean remove(T model);
    boolean update(T data);
    T findById(int id);
    List<T> findAll();
}
