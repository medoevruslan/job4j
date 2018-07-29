package ru.job4j.infostore;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class StoreTest {
    private final List<Store.User> prev = Arrays.asList(new Store.User(1, "first"),
            new Store.User(2, "second"), new Store.User(3, "third"));

    @Test
    public void whenAdded2NewElemetsThen() {
        List<Store.User> curnt = Arrays.asList(new Store.User(1, "first"), new Store.User(2, "second"),
                new Store.User(3, "third"), new Store.User(4, "fourth"), new Store.User(5, "fifth"));
        Store store = new Store();
        store.diff(prev, curnt);
        int result = store.getAdd();
        assertThat(result, is(2));
    }

    @Test
    public void whenRemovedOneElementThen() {
        List<Store.User> curnt = Arrays.asList(new Store.User(1, "first"), new Store.User(2, "second"));
        Store store = new Store();
        store.diff(prev, curnt);
        int result = store.getRem();
        assertThat(result, is(1));
    }

    @Test
    public void whenChanged2ElementsThen() {
        List<Store.User> curnt = Arrays.asList(new Store.User(1, "first"), new Store.User(2, "scd"),
                new Store.User(3, "th"));
        Store store = new Store();
        store.diff(prev, curnt);
        int result = store.getChanged();
        assertThat(result, is(2));
    }
}