package ru.job4j.carstore.service;

import ru.job4j.carstore.dao.ItemDAO;
import ru.job4j.carstore.dao.implementation.ItemManager;
import ru.job4j.carstore.entity.Item;
import ru.job4j.carstore.entity.User;

import javax.servlet.ServletRequest;
import java.sql.Timestamp;
import java.util.List;

public class ItemService extends ItemManager {
    private static final ItemDAO INSTANCE = new ItemService();
    private final ItemManager manager = new ItemManager();

    private ItemService() { }

    public static ItemDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Item entity) {
        return this.manager.add(entity);
    }

    @Override
    public void update(Item entity) {
        this.manager.update(entity);
    }

    @Override
    public void delete(Item entity) {
        this.manager.delete(entity);
    }

    @Override
    public Item findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public Item findByName(String name) {
        return this.manager.findByName(name);
    }

    @Override
    public List<Item> findByModel(User model) {
        return this.manager.findByModel(model);
    }

    @Override
    public List<Item> findByDate(Timestamp time) {
        return this.manager.findByDate(time);
    }

    @Override
    public List<Item> findByManufacturer(String manufacturer) {
        return this.manager.findByManufacturer(manufacturer);
    }

    @Override
    public List<Item> findByManufacturerAndModel(String manufacturer, String model) {
        return this.manager.findByManufacturerAndModel(manufacturer, model);
    }

    @Override
    public List<Item> findAll() {
        return this.manager.findAll();
    }

    @Override
    public void deleteAll() {
        this.manager.deleteAll();
    }

    @Override
    public void setSold(int id, boolean sold) {
        this.manager.setSold(id, sold);
    }

    @Override
    public List<Item> filter(ServletRequest req) {
      return this.manager.filter(req);
    }
}
