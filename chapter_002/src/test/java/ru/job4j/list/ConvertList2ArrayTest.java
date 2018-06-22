package ru.job4j.list;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class ConvertList2ArrayTest {

    @Test
    public void when8ElementsThen9() {
        ConvertList2Array convert = new ConvertList2Array();
        int[][] result = convert.toArray((Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)), 4);
        int[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 0},
        };
        assertThat(expected, is(result));
    }
}
