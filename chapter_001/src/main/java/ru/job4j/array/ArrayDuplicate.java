package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class ArrayDuplicate {

    /**
     * Метод удаляет повторяющиеся элементы в массиве.
     * @param array Массив.
     * @return Массив с уникальными элементами.
     */

    public String[] remove(String[] array) {
        int index = array.length;
        for (int out = 0; out < index; out++) {
            for (int in = out + 1; in < index; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[index - 1];
                    index--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, index);
    }
}
