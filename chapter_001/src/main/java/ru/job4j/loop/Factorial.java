package ru.job4j.loop;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Factorial {

    /**
     * Метод для вычисления факториала числа.
     * @param n Число.
     * @return Факториал числа.
     */

    public int calc(int n) {
        int fact = 1;

        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
