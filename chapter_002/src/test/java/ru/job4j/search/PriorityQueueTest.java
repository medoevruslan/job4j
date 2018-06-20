package ru.job4j.search;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class PriorityQueueTest {

    @Test
    public void whenHighPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("mid", 2));
        queue.put(new Task("low", 3));
        queue.put(new Task("high", 1));
        Task result = queue.take();
        assertThat(result.getDesc(), is("high"));
    }
}
