package ru.job4j.array;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/**
 * Сортировка массива методом слияния.
 */

public class ArrayMerger {

    /**
     * Метод описывает слияние двух отсортированных массив в третий.
     * @param arrayOne Отсортированый массив.
     * @param arrayTwo Отсортированный массив.
     * @return Массив.
     */

    public int[] merge(int[] arrayOne, int[] arrayTwo) {
        int[] merged = new int[arrayOne.length + arrayTwo.length];
        int first = 0, second = 0;
        for (int i = 0; i < merged.length; i++) {
            if (first == arrayOne.length) {
                merged[i] = arrayTwo[second++];
            } else if (second == arrayTwo.length) {
                merged[i] = arrayOne[first++];
            } else if (arrayOne[first] < arrayTwo[second]) {
                merged[i] = arrayOne[first++];
            } else {
                merged[i] = arrayTwo[second++];
            }
        }
        return merged;
    }
}
