package ru.job4j.array;
/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * Сортировка пузырьком.
 */

public class BubbleSort {

    /**
     * Метод использует алгоритм сортировки пузырьком массива чисел.
     * @param array Массив для сортировки.
     * @return Отсортированый массив.
     */

    public int[] sort(int[] array) {
        int tmp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }
}
