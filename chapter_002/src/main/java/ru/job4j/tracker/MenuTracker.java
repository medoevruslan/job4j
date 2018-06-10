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
        this.actions[0] = this.new AddItem();
        this.actions[1] = this.new ShowAll();
        this.actions[2] = new MenuTracker.EditItem();
        this.actions[3] = new MenuTracker.Delete();
        this.actions[4] = new FindById();
        this.actions[5] = new FindByName();
        this.actions[6] = new Exit();
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
    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Добавление новой заявки ---------");
            String name = input.ask("Введите имя заявки : ");
            String desc = input.ask("Введите описание заявки : ");
            Item item = new Item(name, desc, System.currentTimeMillis());
            tracker.add(item);
            System.out.println("--------- Новая заявка с Id " + item.getId() + " создана ---------");
        }

        public String info() {
            return String.format("%s. Add new Item ", key());
        }
    }

    /**
     * Класс для вывода на экран всех заявок.
     */
    private class ShowAll implements UserAction {
        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Список всех заявок : ---------");
            for (Item item : tracker.findAll()) {
                System.out.println("***** Имя заявки : " + item.getName() + " ***** Описание : " + item.getDesc() + " ***** Id : " + item.getId());
            }
        }

        public String info() {
            return String.format("%s. Show all items", key());
        }
    }

    /**
     * Класс реализует редактирование заявки.
     */
    private static class EditItem implements UserAction {
        public int key() {
            return 2;
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

        public String info() {
            return String.format("%s. Edit item", key());
        }
    }

    /**
     * Класс реализует удаление заявки из хранилища.
     */
    private static class Delete implements UserAction {
        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Удаление ---------");
            String id = input.ask("Введите id заявки которую необходимо удалить : ");
            tracker.delete(id);
            System.out.println("--------- Заявка с номером id " + id +  " удалена ---------");
        }

        public String info() {
            return String.format("%s. Delete item", key());
        }
    }

    /**
     * Класс реализует поиск заявки по Id и вывод её на экран.
     */
     class FindById implements UserAction {
        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Поиск заявки по Id ---------");
            String id = input.ask("Введите id заявки");
            Item item = tracker.findById(id);
            if (item != null){
                System.out.println("***** Заявка : " + item.getName() + " ***** Описание : " + item.getDesc());
            } else {
                System.out.println("Заявки с таким Id не найдено");
            }
        }

        public String info() {
            return String.format("%s. Find item by Id", key());
        }
    }

    /**
     * Класс реализует поиск заявки по имени и вывод её на экран.
     */
    class FindByName implements UserAction {
        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Поиск заявки по имени ---------");
            String name = input.ask("Введите имя заявки : ");
            for (Item item : tracker.findByName(name)) {
                System.out.println("Заявка : " + item.getName() + " *****  Описание : " + item.getDesc() + "**** Id : " + item.getId());
            }
        }

        public String info() {
            return String.format("%s. Find items by name", key());
        }
    }

    class Exit implements UserAction {
        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
        }

        public String info() {
            return String.format("%s. Exit program", key());
        }
    }
}
