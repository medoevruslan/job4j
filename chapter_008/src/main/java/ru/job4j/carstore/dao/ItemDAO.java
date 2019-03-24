package ru.job4j.carstore.dao;

import ru.job4j.carstore.entity.Item;
import ru.job4j.carstore.entity.User;

import javax.servlet.ServletRequest;
import java.sql.Timestamp;
import java.util.List;

public interface ItemDAO extends DAO<Item>, ItemRepository {

    int add(Item entity);
    void update(Item entity);
    void delete(Item entity);
    Item findById(int id);
    List<Item> findAll();
    Item findByName(String name);
    List<Item> findByModel(User model);
    void setSold(int id, boolean sold);
    List<Item> findByDate(Timestamp time);
    List<Item> findByManufacturer(String manufacturer);
    List<Item> filter(ServletRequest req);
    void deleteAll();
}
