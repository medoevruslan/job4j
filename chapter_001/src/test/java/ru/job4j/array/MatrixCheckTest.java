package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class MatrixCheckTest {

    @Test
    public void whenDataMonoTrueByTrueThanTrue() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = new boolean[][]{
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        boolean result = matrixCheck.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataMonoFalseByFalseThanFalse() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = new boolean[][]{
                {false, false, false, false},
                {false, false, false, false},
                {false, true, false, false},
                {false, false, false, false}
        };
        boolean result = matrixCheck.mono(input);
        assertThat(result, is(false));
    }
}
