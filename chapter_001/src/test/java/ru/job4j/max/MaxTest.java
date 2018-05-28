package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MaxTest {

    @Test
    public void whenFirstGreaterSecond() {
        Max maximum = new Max();
        int max = maximum.summation(7, 3);
        assertThat(max, is(7));
    }

    @Test
    public void whenFirstLessSecond() {
        Max maximum = new Max();
        int max = maximum.summation(3, 7);
        assertThat(max, is(7));
    }
}
