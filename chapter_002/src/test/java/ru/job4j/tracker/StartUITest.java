package ru.job4j.tracker;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class StartUITest {

    @Test
    public void whenUserAddItemThanTrackerHasItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StabInput(new String[] {"0", "test", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test"));
    }

    @Test
    public void whenUserDeleteItemThan() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test1", "desc1", 123L));
        Item second = tracker.add(new Item("test2", "desc2", 1233L));
        Item third = tracker.add(new Item("test3", "desc3", 1234L));
        Input input = new StabInput(new String[] {"3", second.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[1].getName(), is("test3"));
    }

    @Test
    public void whenUserEditThanTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test", "desc", 123L));
        Input input = new StabInput(new String[] {"2", item.getId(), "editedName", "editedDescr", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("editedName"));
    }

    @Test
    public void whenUserFindByIdThan() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test1", "desc1", 123L));
        Item second = tracker.add(new Item("test2", "desc2", 123L));
        Item third = tracker.add(new Item("test3", "desc3", 123L));
        Input input = new StabInput(new String[] {"4", third.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(third.getId()).getDesc(), is(third.getDesc()));
    }

    @Test
    public void whenUserFindByNameThanTrackerHasTheSameNames() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test1", "desc1", 123L));
        Item second = tracker.add(new Item("test1", "desc2", 1234L));
        Item third = tracker.add(new Item("test3", "desc3", 12345L));
        Input input = new StabInput(new String[] {"5", "test1", "6"});
        new StartUI(input, tracker).init();
        Item[] expexted = {first, second};
        assertThat(tracker.findByName(first.getName()), is(expexted));
    }
}
