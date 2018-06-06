package ru.job4j.tracker;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class TrackerTest {

    @Test
    public void whenAddNewItemThanTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "testDescr", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThanReturnNewName() {
        Tracker tracker = new Tracker();
        Item prev = new Item("prev", "prevDescr", 123L);
        tracker.add(prev);
        Item next = new Item("next", "nextDescr", 1234L);
        next.setId(prev.getId());
        tracker.replace(prev.getId(), next);
        assertThat(tracker.findById(prev.getId()).getName(), is("next"));
    }

    @Test
    public void whenDeleteOneOfThreeTasksThanTrackerHasTwoTask() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescr1", 123L);
        Item item2 = new Item("test2", "testDescr2", 1234L);
        Item item3 = new Item("test3", "testDescr3", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.delete(item2.getId());
        assertThat(tracker.findAll()[1], is(item3));
    }

    @Test
    public void whenTrackerFindByNameThan() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("name1", "descr1", 123L);
        Item item2 = new Item("name2", "descr2", 1234L);
        Item item3 = new Item("name3", "descr3", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findByName("name2")[0].getDesc(), is(item2.getDesc()));
    }
}
