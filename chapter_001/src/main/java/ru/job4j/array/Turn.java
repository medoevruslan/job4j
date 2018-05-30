package ru.job4j.array;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */


public class Turn {

    /**
     * Метод принимает массив и переворачивает его задом на перед.
     * @param array Массив чисел.
     * @return Перевернутый массив.
     */

    public int[] turn(int[] array) {
        int temp;
        for (int i = 0; i < array.length / 2; i++) {
            temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}
