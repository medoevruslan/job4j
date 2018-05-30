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
        ArrayChar arrayChar = new ArrayChar("Hello");
        boolean result = arrayChar.startWith("He");
        assertThat(result, is(true));
    }

    @Test
    public void whenStartWithPrefixThanFalse() {
        ArrayChar arrayChar = new ArrayChar("Hello");
        boolean result = arrayChar.startWith("Hi");
        assertThat(result, is(false));
    }

}
