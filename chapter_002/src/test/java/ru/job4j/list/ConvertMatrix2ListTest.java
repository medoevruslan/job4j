package ru.job4j.list;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class ConvertMatrix2ListTest {

    @Test
    public void when3on3ArrayList6Then() {
        ConvertMatrix2List convert = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        List<Integer> result = convert.toList(input);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expected));
    }
}
