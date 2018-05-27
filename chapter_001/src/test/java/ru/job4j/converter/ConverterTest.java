package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConverterTest {
    @Test
    public void when61RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(61);
        assertThat(result, is(1));
    }

    @Test
    public void when72RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(72);
        assertThat(result, is(1));
    }

    @Test
    public void when1EuroToRubleThan72() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(1);
        assertThat(result, is(72));
    }

    @Test
    public void when1DollarToRubleThan61() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(1);
        assertThat(result, is(61));
    }
}