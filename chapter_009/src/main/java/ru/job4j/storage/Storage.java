package ru.job4j.storage;

import java.util.List;

public interface Storage<E> {

    int add(E e);
    boolean update(E e);
    boolean delete(E e);
    List<E> findAll();


}
