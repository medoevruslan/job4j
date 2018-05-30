package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */


public class TurnTest {
    @Test
    public void whenArrayWithEvenAmountThanTurnedArray() {
        Turn turn = new Turn();
        int[] input = new int[]{1, 3, 5, 7, 9, 11};
        int[] rsl = turn.turn(input);
        int[] expected = new int[]{11, 9, 7, 5, 3, 1};
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenArrayWithOddAmountThanTurnedArray() {
        Turn turn = new Turn();
        int[] input = new int[]{3, 5, 7, 9, 11};
        int[] rsl = turn.turn(input);
        int[] expected = new int[]{11, 9, 7, 5, 3};
        assertThat(rsl, is(expected));
    }
}
