package ru.job4j.tracker;

import java.util.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

/**
 * Хранилище заявок.
 */

public class Tracker {

    /**
     * Переменная для хранения случайных чисел.
     */
    static final Random RN = new Random();

    /**
     * Массив для хранение заявок.
     */
    private final ArrayList<Item> items = new ArrayList();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод удаляет заявку по заданому id.
     * @param id Уникальный номер заявки.
     */
    public void delete(String id) {
        boolean isDeleted = false;
        if (!this.items.isEmpty()) {
            for (Item item : this.items) {
                if (item.getId().equals(id)) {
                    this.items.remove(item);
                    System.out.println("--------- Заявка с номером id " + id + " удалена ---------");
                    isDeleted = true;
                    break;
                }
            }
        }
        if (!isDeleted) {
            System.out.println("Заявка с таким id не найдена");
        }
    }

    /**
     * Метод заменяет заявку на другую.
     * @param id Уникальный номер заявки.
     * @param item Заявку котороую необходимо вставить.
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index) != null && this.items.get(index).getId().equals(id)) {
                item.setId(this.items.get(index).getId());
                this.items.remove(index);
                this.items.add(index, item);
                break;
            }
        }
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(RN.nextInt(100) + System.currentTimeMillis());
    }

    /**
     *  Метод возвращает список всех заявок.
     * @return Список все заявок.
     */
    public ArrayList<Item> findAll() {
        return this.items;
    }

    /**
     * Метод производид поиск по хранилищу по заданному имени.
     * @param key Имя искомого элемнта.
     * @return Список всех заявок с искомым именем.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> list = new ArrayList<>();
        if (!this.items.isEmpty()) {
            for (Item item : this.items) {
                if (item != null && item.getName().equals(key)) {
                    list.add(item);
                }
            }
        }
        return list;
    }

    /**
     * Метод производит поиск по спику по заданному Id.
     * @param id Уникальный номер заявки.
     * @return null или искомую заявку.
     */

    public Item findById(String id) {
        Item result = null;
        if (!this.items.isEmpty()) {
            for (Item item : this.items) {
                if (item.getId().equals(id)) {
                    result = item;
                    break;
                }
            }
        }
        return result;
    }
}
