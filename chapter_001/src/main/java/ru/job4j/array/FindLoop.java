package ru.job4j.array;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class FindLoop {

    /**
     * Метод поиска элемента в массиве.
     * @param data Массив.
     * @param el Искомое значение.
     * @return Индекс элемента, если такого нет то возвращает -1.
     */

    public int indexOf(int[] data, int el) {
        int rsl = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }
}
