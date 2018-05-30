package ru.job4j.array;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Square {

    /**
     * Метод заполняет массив элементами от 1 до bound  возведенными в квадрат.
     * @param bound Размер массива.
     * @return Массив.
     */

    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound; i++) {
            rst[i] = (i + 1) * (i + 1);
        }
        return rst;

    }
}
