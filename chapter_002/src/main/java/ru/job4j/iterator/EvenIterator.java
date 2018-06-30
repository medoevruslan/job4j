package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class EvenIterator implements Iterator {
    private final int[] array;
    private int index = 0;

    public EvenIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean isTrue = false;
        for (int i = index; i < array.length; i++) {
            if ((array[i] % 2 == 0)) {
                isTrue = true;
                break;
            }
        }
        return array.length > index && isTrue;
    }

    @Override
    public Object next() {
        int result = 0;
        for (; index < array.length; index++) {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            if ((array[index] % 2) == 0) {
                result = array[index++];
                break;
            }
        }
        return result;
    }
}
