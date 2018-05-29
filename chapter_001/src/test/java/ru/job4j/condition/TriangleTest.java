package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TriangleTest {
    @Test
    public void whenSetPointsTriangleAreaIs() {
        Point a = new Point(4, 8);
        Point b = new Point(3, 9);
        Point c = new Point(2, 6);

        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();
        double expected = 2D;

        assertThat(result, closeTo(2.0, 0.1));
    }

}
