package ru.job4j.todolist.service;

import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.persistent.ItemRepository;
import ru.job4j.todolist.persistent.implementation.ItemManager;

import java.util.List;

/**
 * Service which manages the Item entity.
 */
public class ItemService implements ItemRepository {
    private final static ItemService INSTANCE = new ItemService();
    private final ItemManager manager = new ItemManager();

    private ItemService() { }

    public static ItemRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Item item) {
        this.manager.add(item);
    }

    @Override
    public void update(Item item) {
        this.manager.update(item);
    }

    @Override
    public void delete(Item item) {
        this.manager.delete(item);
    }

    @Override
    public List<Item> findAll() {
        return this.manager.findAll();
    }

    @Override
    public List<Item> findUndone() {
        return this.manager.findUndone();
    }
}
