package ru.job4j.array;


/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */


public class Matrix {

    /**
     * Метод создает матрицу в виде таблицы умножения.
     * @param size Размер матрицы.
     * @return Матрица в виде таблицы умноженя.
     */

    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
