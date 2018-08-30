package ru.job4j.tracker;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class TrackerTest {
    private final File config
            = new File("/Users/ruslanmd/projects/job4j/chapter_002/src/main/resources/tracker_config.properties");

    @Test
    public void whenAddNewItemThanTrackerHasSameItem() throws Exception {
        try (Tracker tracker = new Tracker(config)) {
            tracker.clearTable();
            Item item = new Item("test", "testDescr", 123L);
            tracker.add(item);
            assertThat(tracker.findAll().get(0), is(item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReplaceNameThanReturnNewName() throws Exception {
        try (Tracker tracker = new Tracker(config)) {
            tracker.clearTable();
            Item prev = new Item("prev", "prevDescr", 123L);
            tracker.add(prev);
            Item next = new Item("next", "nextDescr", 1234L);
            tracker.replace(prev.getId(), next);
            assertThat(tracker.findById(prev.getId()).getName(), is("next"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteOneOfThreeTasksThanTrackerHasTwoTask() throws Exception {
        try (Tracker tracker = new Tracker(config)) {
            tracker.clearTable();
            Item item1 = new Item("test1", "testDescr1", 123L);
            Item item2 = new Item("test2", "testDescr2", 1234L);
            Item item3 = new Item("test3", "testDescr3", 12345L);
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            tracker.delete(item2.getId());
            assertThat(tracker.findAll().get(1), is(item3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenTrackerFindByNameThan() throws Exception {
        try (Tracker tracker = new Tracker(config)) {
            tracker.clearTable();
            Item item1 = new Item("name1", "descr1", 123L);
            Item item2 = new Item("name2", "descr2", 1234L);
            Item item3 = new Item("name3", "descr3", 12345L);
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertThat(tracker.findByName("name2").get(0).getDesc(), is(item2.getDesc()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
