package ru.job4j.tracker;

import java.util.Date;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class StartUI {
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDID = "4";
    private static final String FINDNAME = "5";
    private static final String EXIT = "6";

    private Input input;
    private Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод инициализации интерфейса взаимодейтвия с пользователем.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню :");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showTasks();
            } else if (EDIT.equals(answer)) {
                this.edit();
            } else if (DELETE.equals(answer)) {
                this.delete();
            } else if (FINDID.equals(answer)) {
                this.findId();
            } else if (FINDNAME.equals(answer)) {
                this.findName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Меню выбора действий.
     */
    private void showMenu() {
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit");

    }

    /**
     * Метод реализует добавленяи новой заявки в хранилище.
     */
    private void createItem() {
        System.out.println("--------- Добавление новой заявки ---------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc, System.currentTimeMillis());
        this.tracker.add(item);
        System.out.println("--------- Новая заявка с Id " + item.getId() + " создана ---------");
    }

    /**
     * Метод для вывода на экран всех заявок.
     */
    private void showTasks() {
        System.out.println("--------- Список всех заявок : ---------");
        for (Item item : this.tracker.findAll()) {
            System.out.println("***** Имя заявки : " + item.getName() + " ***** Описание : " + item.getDesc() + " ***** Id : " + item.getId());
        }
    }

    /**
     * Метод реализует редактирование заявки.
     */
    private void edit() {
        System.out.println("--------- Редактирование ---------");
        String id = this.input.ask("Введите id заявки которую необходимо изменить :");
        String name = this.input.ask("Новое имя заявки :");
        String desc = this.input.ask("Введите описание :");
        Item item = new Item(name, desc, System.currentTimeMillis());
        this.tracker.replace(id, item);
        System.out.println("--------- Заявка по Id " + item.getId() + " отредактирована ---------");
    }

    /**
     * Метод реализует удаление заявки из хранилища.
     */
    private void delete() {
        System.out.println("--------- Удаление ---------");
        String id = this.input.ask("Введите id заявки которую необходимо удалить");
        this.tracker.delete(id);
        System.out.println("--------- Заявка с номером id " + id +  " удалена ---------");
    }

    /**
     * Метод реализует поиск заявки по Id и вывод её на экран.
     */
    private void findId() {
        System.out.println("--------- Поиск заявки по Id ---------");
        String id = this.input.ask("Введите id заявки");
        Item item = this.tracker.findById(id);
        System.out.println("***** Заявка : " + item.getName() + " ***** Описание : " + item.getDesc());
    }

    /**
     * Метод реализует поиск заявки по имени и вывод её на экран.
     */
    private void findName() {
        System.out.println("--------- Поиск заявки по имени ---------");
        String name = this.input.ask("Введите имя заявки");
        for (Item item : this.tracker.findByName(name)) {
            System.out.println("Заявка : " + item.getName() + " *****  Описание : " + item.getDesc() + "**** Id : " + item.getId());
        }
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
