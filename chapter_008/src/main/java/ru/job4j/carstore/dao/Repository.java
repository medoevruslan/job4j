package ru.job4j.carstore.dao;

import java.sql.Timestamp;
import java.util.List;

public interface Repository<E, M> {
    E findByName(String name);
    List<E> findByModel(M model);
    List<E> findByDate(Timestamp time);
}
