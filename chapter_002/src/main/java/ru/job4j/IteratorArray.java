package ru.job4j;

import java.util.Iterator;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class IteratorArray implements Iterator {
    private final int[][]array;
    private int row = 0;
    private int col = 0;

    public IteratorArray(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return array[row].length > col || array.length - 1 > row;
    }

    @Override
    public Object next() {
        if (col == array[row].length) {
            row++;
            col = 0;
        }
        return array[row][col++];
    }
}
