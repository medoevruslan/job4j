package ru.job4j.sort;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class SortUserTest {

    @Test
    public void whenSortUserByAgeThaen() {
        List<User> users = Arrays.asList(
                new User("2", 18),
                new User("3", 23),
                new User("1", 11),
                new User("4", 45)
        );
        List<User> expected = Arrays.asList(
                new User("1", 11),
                new User("2", 18),
                new User("3", 23),
                new User("4", 45)
        );
        Set<User> result = new SortUser().sort(users);
        ArrayList<User> actual = new ArrayList<>(result);
        assertEquals(expected, actual);
    }

    @Test
    public void whenSortedByNameLengthThen() {
        List<User> users = Arrays.asList(
                new User("1234", 18),
                new User("12", 23),
                new User("123", 11),
                new User("1", 45)
        );
        List<User> expected = Arrays.asList(
                new User("1", 45),
                new User("12", 23),
                new User("123", 11),
                new User("1234", 18)
                );
        List<User> result = new SortUser().sortNameLength(users);
        assertEquals(expected, result);
    }

    @Test
    public void whenSortByFieldsThen() {
        List<User> users = Arrays.asList(
                new User("Игорь", 18),
                new User("Михаил", 23),
                new User("Игорь", 11),
                new User("Михаил", 45)
        );
        List<User> expected = Arrays.asList(
                new User("Игорь", 11),
                new User("Игорь", 18),
                new User("Михаил", 23),
                new User("Михаил", 45)
        );
        List<User> result = new SortUser().sortByFields(users);
        assertEquals(expected, result);
    }
}
