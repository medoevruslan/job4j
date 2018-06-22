package ru.job4j.coffemaker;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */


public class CoffeMakerTest {

    @Test
    public void whenValue100Price35Than() {
      CoffeMaker coffe = new CoffeMaker();
      int[] result = coffe.changes(100, 35);
      int[] expexted = {10, 10, 10, 10, 10, 10, 5};
      assertThat(result, is(expexted));
      }

      @Test
    public void whenValue50Price14Than() {
        CoffeMaker coffe = new CoffeMaker();
        int[] result = coffe.changes(50, 12);
        int[] expected = {10, 10, 10, 5, 2, 1};
        assertThat(result, is(expected));
      }
}
