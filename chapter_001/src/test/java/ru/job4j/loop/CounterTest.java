package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



public class CounterTest {

    @Test
    public void whenSumOfNumbersFromOneToTenThanFiftytyix() {
        Counter counter = new Counter();
         int sum = counter.add(1, 15);
         assertThat(sum, is(56));
    }
}
