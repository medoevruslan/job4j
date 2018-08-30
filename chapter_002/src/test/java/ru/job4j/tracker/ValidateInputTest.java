package ru.job4j.tracker;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class ValidateInputTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final File config
            = new File("/Users/ruslanmd/projects/job4j/chapter_002/src/main/resources/tracker_config.properties");

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void setOut() {
        System.setOut(stdout);
    }

    @Test
    public void whenInputWordThan() throws Exception {
        try (Tracker tracker = new Tracker(config)) {
            ValidateInput input = new ValidateInput(new StabInput(new String[]{"invalid", "2"}));
            MenuTracker menu = new MenuTracker(input, tracker);
            menu.fillActions();
            input.ask("question", menu.actions);
            assertThat(this.out.toString(), is("Please, select key from menu.\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenInputInvalidNumber() throws Exception {
        try (Tracker tracker = new Tracker(config)) {
        ValidateInput input = new ValidateInput(new StabInput(new String[] {"7", "6"}));
            MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
        input.ask("question", menu.actions);
        assertThat(this.out.toString(), is("Please, enter correct number of menu.\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
