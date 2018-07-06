package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleSetTest {

    SimpleSet<Integer> set;

    @Before
    public void initTest() {
        set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
    }

    @Test
    public void whenSetAlreadyHasElementThanSizeIsDoNotChange() {
        set.add(4);
        set.add(3);
        set.add(1);
        assertThat(set.getSize(), is(4));
    }
}