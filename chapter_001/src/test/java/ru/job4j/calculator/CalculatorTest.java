package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddOneAndOneThanTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractThreeOnTWoThanOne() {
        Calculator calc = new Calculator();
        calc.subtract(3D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivThreeOnThreeThanOne() {
        Calculator calc = new Calculator();
        calc.div(3D, 3D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultipleTwoOnTwoThanFour() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }

}