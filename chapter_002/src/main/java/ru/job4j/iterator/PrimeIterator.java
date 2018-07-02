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

    private boolean isPrime(int num) {
        boolean rst = false;
        for (int i = num; i < array.length; i++) {
            int count = 0;
            for (int j = 1; j <= array[i]; j++) {
                if (array[i] % j == 0) {
                    count++;
                }
            }
            if (count == 2) {
                rst = true;
                break;
            }
        }
        return rst;
    }

    @Override
    public boolean hasNext() {
        return array.length > index && isPrime(index);
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
