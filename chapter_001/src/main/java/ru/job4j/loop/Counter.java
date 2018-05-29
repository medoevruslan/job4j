package ru.job4j.loop;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Counter {

    int sum = 0;

    /**
     * Метод вычесляет сумму всех четных чисел
     * @param start
     * @param finish
     * @return Сумма четных чисел
     */
    public int add(int start, int finish) {
        for (int i = start; i <= finish ; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;

    }
}
