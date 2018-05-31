package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class ArrayDuplicateTest {
    @Test
    public void whenArrayHasTwoDuplicatesThan() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = new String[]{"Hello", "Hello", "My", "World", "Name", "Hello"};
        String[] result = arrayDuplicate.remove(input);
        String[] expected = new String[]{"Hello", "My", "World", "Name"};
        assertThat(result, arrayContainingInAnyOrder(expected));
    }

    @Test
    public void whenArrayAllDeplicateThan() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"Hi", "Hi", "Hi"};
        String[] result = arrayDuplicate.remove(input);
        String[] expected = {"Hi"};
        assertThat(result, arrayContainingInAnyOrder(expected));
    }
}
