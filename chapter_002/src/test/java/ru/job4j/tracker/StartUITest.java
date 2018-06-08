package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Tracker tracker = new Tracker();
    private Item first, second, third;

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOut() {
        System.setOut(stdout);
    }

    private void addItems() {
        first = tracker.add(new Item("test1", "desc1", 123L));
        second = tracker.add(new Item("test2", "desc2", 1233L));
        third = tracker.add(new Item("test3", "desc3", 1234L));
    }

    @Test
    public void whenUserAddItemThanTrackerHasItemWithSameName() {
        Input input = new StabInput(new String[] {"0", "test", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test"));
    }

    @Test
    public void whenUserEditThanTrackerHasUpdatedValue() {
        this.addItems();
        Input input = new StabInput(new String[] {"2", first.getId(), "editedName", "editedDescr", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(first.getId()).getName(), is("editedName"));
    }

    @Test
    public void whenUserFindByIdThan() {
        this.addItems();
        Input input = new StabInput(new String[] {"4", third.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(third.getId()).getDesc(), is(third.getDesc()));
    }

    @Test
    public void whenUserFindByNameThanTrackerHasTheSameNames() {
        this.addItems();
        Input input = new StabInput(new String[] {"5", "test1", "6"});
        new StartUI(input, tracker).init();
        Item[] expexted = {first};
        assertThat(tracker.findByName(first.getName()), is(expexted));
    }

    @Test
    public void whenUserShowAllItemsThan() {
        Item item = tracker.add(new Item("test", "desc", 123L));
        Input input = new StabInput(new String[] {"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append("0. Add new Item\n")
                        .append("1. Show all items\n")
                        .append("2. Edit item\n")
                        .append("3. Delete item\n")
                        .append("4. Find item by Id\n")
                        .append("5. Find items by name\n")
                        .append("6. Exit\n")
                        .append("--------- Список всех заявок : ---------\n")
                        .append("***** Имя заявки : test ***** Описание : desc ***** Id : " + item.getId() + "\n")
                        .append("0. Add new Item\n")
                        .append("1. Show all items\n")
                        .append("2. Edit item\n")
                        .append("3. Delete item\n")
                        .append("4. Find item by Id\n")
                        .append("5. Find items by name\n")
                        .append("6. Exit\n").toString()
                )
        );
    }

    @Test
    public void whenUserDeleteItemThan() {
        this.addItems();
        Input input = new StabInput(new String[] {"3", second.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[1].getName(), is("test3"));
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append("0. Add new Item\n")
                        .append("1. Show all items\n")
                        .append("2. Edit item\n")
                        .append("3. Delete item\n")
                        .append("4. Find item by Id\n")
                        .append("5. Find items by name\n")
                        .append("6. Exit\n")
                        .append("--------- Удаление ---------\n")
                        .append("--------- Заявка с номером id " + second.getId() +  " удалена ---------\n")
                        .append("0. Add new Item\n")
                        .append("1. Show all items\n")
                        .append("2. Edit item\n")
                        .append("3. Delete item\n")
                        .append("4. Find item by Id\n")
                        .append("5. Find items by name\n")
                        .append("6. Exit\n").toString()
                )
        );
    }
}
