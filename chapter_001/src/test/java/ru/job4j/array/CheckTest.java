package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class CheckTest {
    @Test
    public void whenArrayCellsIsSame() {
        Check check = new Check();
        boolean[] input = new boolean[]{false, false, false};
        boolean rsl = check.mono(input);
        boolean expected = true;
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenArrayCellsIsNotSame() {
        Check check = new Check();
        boolean[] input = new boolean[]{true, true, false, true};
        boolean rsl = check.mono(input);
        boolean expected = false;
        assertThat(rsl, is(expected));
    }
}
