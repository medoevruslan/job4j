package ru.job4j.pseudo;

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
    @Test
    public void whenPaintTriangleThan() {
        Paint paint = new Paint();
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        paint.draw(new Triangle());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append("   ^   ").append(System.lineSeparator())
                        .append("  ^^^  ").append(System.lineSeparator())
                        .append(" ^^^^^ ").append(System.lineSeparator())
                        .append("^^^^^^^").append(System.lineSeparator())
                        .toString()
                )
        );
        System.setOut(stdout);
    }

    @Test
    public void whenPaintSquareThan() {
        Paint paint = new Paint();
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        paint.draw(new Square());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("*******").append(System.lineSeparator())
                .append("*     *").append(System.lineSeparator())
                .append("*     *").append(System.lineSeparator())
                .append("*******").append(System.lineSeparator())
                .toString()
                )
        );
        System.setOut(stdout);
    }
}
