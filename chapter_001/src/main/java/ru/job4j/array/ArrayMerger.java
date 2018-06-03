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
        int[] mergedArray = new int[arrayOne.length + arrayTwo.length];
        int a1 = 0, a2 = 0;
        for (int i = 0; i < mergedArray.length; i++) {
            if (a1 == arrayOne.length) {
                mergedArray[i] = arrayTwo[a2++];
            } else if (a2 == arrayTwo.length) {
                mergedArray[i] = arrayOne[a1++];
            } else if (arrayOne[a1] < arrayTwo[a2]) {
                mergedArray[i] = arrayOne[a1++];
            } else {
                mergedArray[i] = arrayTwo[a2++];
            }
        }
        return mergedArray;
    }
}
