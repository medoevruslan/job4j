package ru.job4j.tracker;

import java.util.Date;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class StartUI {
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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        String key;
        do {
            menu.showMenu();
            key = this.input.ask("Please, select : ");
            menu.select(key);
        } while (!"6".equals(key));
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
