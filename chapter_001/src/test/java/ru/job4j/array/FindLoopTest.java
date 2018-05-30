package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class FindLoopTest {

    @Test
    public void whenArraySearchFiveThan0() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{5, 10, 3};
        int el = 5;
        int rsl = find.indexOf(input, el);
        int expected = 0;
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenArraySearchSevenThanThree() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{3, 9, 8, 7, 5};
        int el = 7;
        int rsl = find.indexOf(input, el);
        int expected = 3;
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenArrayNotContainElemen() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{0, 2, 7, 9, 5};
        int el = 3;
        int rsl = find.indexOf(input, el);
        int expected = -1;
        assertThat(rsl, is(expected));
    }
}
