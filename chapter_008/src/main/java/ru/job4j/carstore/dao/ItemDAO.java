package ru.job4j.carstore.dao;

import ru.job4j.carstore.entity.Item;

import java.util.List;

public interface ItemDAO extends DAO<Item> {

    int add(Item entity);
    void update(Item entity);
    void delete(Item entity);
    Item findById(int id);
    List<Item> findAll();
    void setSold(int id, boolean sold);
    void deleteAll();
}
