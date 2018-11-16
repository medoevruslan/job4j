package ru.job4j.todolist.persistent;

import ru.job4j.todolist.model.Item;

import java.util.List;

public interface ItemRepository {

    void add(Item item);
    void update(Item item);
    void delete(Item item);
    List<Item> findAll();
    List<Item> findUndone();
}
