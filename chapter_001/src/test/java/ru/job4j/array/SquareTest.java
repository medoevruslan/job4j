package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class SquareTest {

    @Test
    public void whenBound3Than149() {
        int bound = 3;
        Square square = new Square();
        int[] rsl = square.calculate(bound);
        int[] expected = new int[]{1, 4, 9};
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenBound5Than1391625() {
        int bound = 5;
        Square square = new Square();
        int[] rsl = square.calculate(bound);
        int[] expected  = new int[]{1, 4, 9, 16, 25};
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenBound4Than14916() {
        int bound = 4;
        Square square = new Square();
        int[] rsl = square.calculate(bound);
        int[] expected = new int[]{1, 4, 9, 16};
        assertThat(rsl, is(expected));
    }
}
