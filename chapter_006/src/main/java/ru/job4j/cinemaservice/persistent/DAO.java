package ru.job4j.cinemaservice.persistent;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    int add(T model);
    boolean update(T model);
    boolean remove(T model);
    Optional<T> findById(int id);
    List<T> findAll();
}
