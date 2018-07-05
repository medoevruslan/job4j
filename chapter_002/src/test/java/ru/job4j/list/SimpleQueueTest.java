package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleQueueTest {

    SimpleQueue<Integer> queue;

    @Before
    public void beforeTests() {
        queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
    }

    @Test
    public void whenPollTwoElementsShouldBeTwo() {
       queue.poll();
       assertThat(queue.poll(), is(2));
    }

    @Test
    public void whenPushFourElementsShouldBeSizeFour() {
        assertThat(queue.getSize(), is(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenQueueIsEmptyShouldBeThrowException() {
        queue = new SimpleQueue<>();
        queue.poll();
    }
}