package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MaxTest {

    @Test
    public void whenFirstGreaterSecond() {
        Max maximum = new Max();
        int max = maximum.max(7, 3);
        assertThat(max, is(7));
    }

    @Test
    public void whenFirstLessSecond() {
        Max maximum = new Max();
        int max = maximum.max(3, 7);
        assertThat(max, is(7));
    }

    @Test
    public void whenThirdIsGreater() {
        Max max = new Max();
        int maximum = max.max(1, 3, 7);
        assertThat(maximum, is(7));
    }
}
