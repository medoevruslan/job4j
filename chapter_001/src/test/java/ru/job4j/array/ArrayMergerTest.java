package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class ArrayMergerTest {

    @Test
    public void whenUseArraymergerThanMergedArray() {
        ArrayMerger array = new ArrayMerger();
        int[] arr1 = {1, 4, 7, 9, 13};
        int[] arr2 = {2, 3, 5, 8};
        int[] result = array.merge(arr1, arr2);
        int[] expctd = {1, 2, 3, 4, 5, 7, 8, 9, 13};
        assertThat(result, is(expctd));
    }

}
