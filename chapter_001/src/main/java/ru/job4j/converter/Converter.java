package ru.job4j.converter;

/**
 * Конвертор валюты.
 */

public class Converter {
    /**
     *  Конвертируем рубли в евро.
     * @param value рубли
     * @return Евро
     */

    public int rubleToEuro(int value) {
        return 72 / value;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары
     */

    public int rubleToDollar(int value) {
        return 61 / value;
    }

    /**
     *  Конвертируем евро в рубли.
     * @param value евро
     * @return Рубли
     */

    public int euroToRuble(int value) {
        return value * 72;
    }

    /**
     *  Конвертируем доллары в рубли.
     * @param value доллары
     * @return Рубли
     */

    public int dollarToRuble(int value) {
        return value * 61;
    }
}
