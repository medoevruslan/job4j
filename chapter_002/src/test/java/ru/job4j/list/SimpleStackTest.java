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

public class SimpleStackTest {

    SimpleStack<Integer> stack;

    @Before
    public void beforeTests() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
    }

    @Test
    public void whenPollThreeElementsThanShouldBeTwoAndSize1() {
        stack.poll();
        stack.poll();
        assertThat(stack.poll(), is(2));
        assertThat(stack.getSize(), is(1));
    }

    @Test
    public void whenPushFourElementsThanSizeFour() {
        assertThat(stack.getSize(), is(4));
    }
}