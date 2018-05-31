package ru.job4j.array;

import org.junit.Test;
import  static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class MatrixTest {

    @Test
    public void whenArraysSizeFiveThanFiveOnFive() {
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiple(5);
        int[][] epected = new int[][]{
                {1, 2, 3, 4, 5},
                {2, 4, 6, 8, 10},
                {3, 6, 9, 12, 15},
                {4, 8, 12, 16, 20},
                {5, 10, 15, 20, 25}
        };
        assertThat(result, is(epected));
    }

    @Test
    public void whenArraysSizeThreeThanThreeOnThree() {
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiple(3);
        int[][] expected = new int[][]{
                {1, 2, 3},
                {2, 4, 6},
                {3, 6, 9}
        };
        assertThat(result, is(expected));
    }
}
