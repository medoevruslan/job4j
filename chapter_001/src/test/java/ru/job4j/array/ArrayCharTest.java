package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class ArrayCharTest {
    @Test
    public void whenStartWithPrefixThanTrue() {
        ArrayChar array = new ArrayChar("Hello");
        boolean result = array.startWith("He");
        assertThat(result, is(true));
    }

    @Test
    public void whenStartWithPrefixThanFalse() {
        ArrayChar array = new ArrayChar("Hello");
        boolean result = array.startWith("Hi");
        assertThat(result, is(false));
    }

}
