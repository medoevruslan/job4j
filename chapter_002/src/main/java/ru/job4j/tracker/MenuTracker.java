package ru.job4j.tracker;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */



public class MenuTracker {
    private Tracker tracker;
    private Input input;
    public UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem(0, "Add new Item ");
        this.actions[1] = this.new ShowAll(1, "Show all items");
        this.actions[2] = new MenuTracker.EditItem(2, "Edit item");
        this.actions[3] = new MenuTracker.Delete(3, "Delete item");
        this.actions[4] = new FindById(4, "Find item by Id");
        this.actions[5] = new FindByName(5, "Find items by name");
        this.actions[6] = new Exit(6, "Exit program");
    }

    /**
     * Меню выбора действий.
     */
    public void showMenu() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Ключ соответсвующий пункту меню.
     * @param key Пункт меню.
     */
    public void select(int key) {
        this.actions[key].execute(input, tracker);

    }

    /**
     * Класс реализует добавленяи новой заявки в хранилище.
     */
    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Добавление новой заявки ---------");
            String name = input.ask("Введите имя заявки : ");
            String desc = input.ask("Введите описание заявки : ");
            Item item = new Item(name, desc, System.currentTimeMillis());
            tracker.add(item);
            System.out.println("--------- Новая заявка с Id " + item.getId() + " создана ---------");
        }
    }

    /**
     * Класс для вывода на экран всех заявок.
     */
    private class ShowAll extends BaseAction {

        public ShowAll(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Список всех заявок : ---------");
            for (Item item : tracker.findAll()) {
                System.out.println("***** Имя заявки : " + item.getName() + " ***** Описание : " + item.getDesc() + " ***** Id : " + item.getId());
            }
        }
    }

    /**
     * Класс реализует редактирование заявки.
     */
    private static class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Редактирование ---------");
            String id = input.ask("Введите id заявки которую необходимо изменить : ");
            String name = input.ask("Новое имя заявки : ");
            String desc = input.ask("Введите описание : ");
            Item item = new Item(name, desc, System.currentTimeMillis());
            tracker.replace(id, item);
            if (item.getId() != null) {
                System.out.println("--------- Заявка по Id " + item.getId() + " отредактирована ---------");
            } else {
                System.out.println("Заявки с таким Id не существует");
            }
        }
    }

    /**
     * Класс реализует удаление заявки из хранилища.
     */
    private static class Delete extends BaseAction {

        public Delete(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Удаление ---------");
            String id = input.ask("Введите id заявки которую необходимо удалить : ");
            tracker.delete(id);
        }
    }

    /**
     * Класс реализует поиск заявки по Id и вывод её на экран.
     */
     class FindById extends BaseAction {

        public FindById(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Поиск заявки по Id ---------");
            String id = input.ask("Введите id заявки");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("***** Заявка : " + item.getName() + " ***** Описание : " + item.getDesc());
            } else {
                System.out.println("Заявки с таким Id не найдено");
            }
        }
    }

    /**
     * Класс реализует поиск заявки по имени и вывод её на экран.
     */
    class FindByName extends BaseAction {

        public FindByName(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Поиск заявки по имени ---------");
            String name = input.ask("Введите имя заявки : ");
            for (Item item : tracker.findByName(name)) {
                System.out.println("Заявка : " + item.getName() + " *****  Описание : " + item.getDesc() + "**** Id : " + item.getId());
            }
        }
    }

    class Exit extends BaseAction {

        public Exit(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
        }
    }
}
