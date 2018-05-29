package ru.job4j.loop;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class FactorialTest {

    @Test
    public void whenNumberFiveThan120() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        assertThat(result, is(120));
    }

    @Test
    public void whenZeroThanOne() {
        Factorial factorial = new Factorial();
        int rslt = factorial.calc(0);
        assertThat(rslt, is(1));
    }
}
