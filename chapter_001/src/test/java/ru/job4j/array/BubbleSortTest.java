package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThanSortedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = new int[]{3, 7, 2, 0, 5, 9, 4, 1, 6, 8};
        int[] result = bubbleSort.sort(input);
        int[] expected = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(result, is(expected));
    }
}
