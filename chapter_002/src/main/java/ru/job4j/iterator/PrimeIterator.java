package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class PrimeIterator implements Iterator {
    private final int[] array;
    private int index = 0;

    public PrimeIterator(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean isTrue = false;
        for (int pointer = index; pointer < array.length; pointer++) {
            if (array[pointer] > 1 && ((array[pointer] % 2) != 0 || array[pointer] == 2)) {
                isTrue = true;
                break;
            }
        }
        return array.length > index && isTrue;
    }

    @Override
    public Object next() {
        int rst = 0;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (index < array.length) {
            if (array[index] > 1 && (array[index]  == 2 || (array[index] % 2) != 0)) {
                rst = array[index++];
                break;
            }
            index++;
        }
        return rst;
    }
}
