package ru.job4j.array;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class MatrixCheck {

    /**
     * Метод проверяет элементы матрицы на идентичность
     * @param data Двухмерный массив.
     * @return true or false.
     */
    public boolean mono(boolean[][] data) {
        boolean tmp = data[0][0];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (i - j == 0) {
                    if (data[i][j] != tmp || data[i][data.length - 1 - i] != tmp) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
