package ru.job4j.carstore.dao;

public interface Repository<E> {
    E findByName(String name);
}
