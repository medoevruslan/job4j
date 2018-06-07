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
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод удаляет заявку по заданому id.
     * @param id Уникальный номер заявки.
     */
    public void delete(String id) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                this.items[index] = null;
                position--;
                System.arraycopy(this.items, index + 1, this.items, index, position);
                break;
            }
        }
    }

    /**
     * Метод заменяет заявку на другую.
     * @param id Уникальный номер заявки.
     * @param item Заявку котороую необходимо вставить.
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(id)) {
                item.setId(this.items[index].getId());
                this.items[index] = item;
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
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index < this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Метод производид поиск по хранилищу по заданному имени.
     * @param key Имя искомого элемнта.
     * @return Список всех заявок с искомым именем.
     */
    public Item[] findByName(String key) {
        Item[] temp = new Item[this.position];
        int i = 0;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index] != null && this.items[index].getName().equals(key)) {
                temp[i++] = this.items[index];
            }
        }
        return Arrays.copyOf(temp, i);
    }

    /**
     * Метод производит поиск по спику по заданному Id.
     * @param id Уникальный номер заявки.
     * @return null или искомую заявку.
     */

    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
