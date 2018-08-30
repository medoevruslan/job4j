package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

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
    private final File config
            = new File("/Users/ruslanmd/projects/job4j/chapter_002/src/main/resources/tracker_config.properties");

    private Item first, second, third;

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOut() {
        System.setOut(stdout);
    }

    private void addItems(Tracker tracker) {
        first = tracker.add(new Item("test1", "desc1", 123L));
        second = tracker.add(new Item("test2", "desc2", 1233L));
        third = tracker.add(new Item("test3", "desc3", 1234L));
    }

    @Test
    public void whenUserAddItemThanTrackerHasItemWithSameName() {
        try (Tracker tracker = new Tracker(config)) {
            tracker.clearTable();
            this.addItems(tracker);
            Input input = new StabInput(new String[]{"0", "test", "desc", "6"});
            new StartUI(input, tracker).init();
            assertThat(tracker.findAll().get(3).getName(), is("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenUserEditThanTrackerHasUpdatedValue() {
        try (Tracker tracker = new Tracker(config)) {
            tracker.clearTable();
            this.addItems(tracker);
            Input input = new StabInput(new String[]{"2", String.valueOf(first.getId()), "editedName", "editedDescr", "6"});
            new StartUI(input, tracker).init();
            assertThat(tracker.findById(first.getId()).getName(), is("editedName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenUserFindByIdThan() {
        try (Tracker tracker = new Tracker(config)) {
            tracker.clearTable();
            this.addItems(tracker);
            Input input = new StabInput(new String[]{"4", String.valueOf(third.getId()), "6"});
            new StartUI(input, tracker).init();
            assertThat(tracker.findById(third.getId()).getDesc(), is(third.getDesc()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenUserFindByNameThanTrackerHasTheSameNames() {
        try (Tracker tracker = new Tracker(config)) {
            tracker.clearTable();
            this.addItems(tracker);
            Input input = new StabInput(new String[] {"5", "test1", "6"});
            new StartUI(input, tracker).init();
            List<Item> expected = Arrays.asList(first);
            assertThat(tracker.findByName(first.getName()), is(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
