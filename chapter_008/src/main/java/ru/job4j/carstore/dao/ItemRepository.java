package ru.job4j.carstore.dao;

import ru.job4j.carstore.entity.Item;
import ru.job4j.carstore.entity.User;

import java.util.List;

public interface ItemRepository extends Repository<Item, User> {
    Item findByName(String name);
    List<Item> findByModel(User model);
    List<Item> findByManufacturer(String manufacturer);
    List<Item> findByManufacturerAndModel(String manufacturer, String model);
}
