package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class PaintTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOut() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOutput() {
        System.setOut(stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenPaintTriangleThan() {
        Paint paint = new Paint();
        paint.draw(new Triangle());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append("   ^   ").append(System.lineSeparator())
                        .append("  ^^^  ").append(System.lineSeparator())
                        .append(" ^^^^^ ").append(System.lineSeparator())
                        .append("^^^^^^^").append(System.lineSeparator())
                        .toString()
                )
        );
    }

    @Test
    public void whenPaintSquareThan() {
        Paint paint = new Paint();
        paint.draw(new Square());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("*******").append(System.lineSeparator())
                .append("*     *").append(System.lineSeparator())
                .append("*     *").append(System.lineSeparator())
                .append("*******").append(System.lineSeparator())
                .toString()
                )
        );
    }
}
