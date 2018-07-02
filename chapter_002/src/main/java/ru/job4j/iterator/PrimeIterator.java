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

        private boolean isPrime(int n) {
            boolean found = true;
            for (int j = 2; (j * j <= n); j++) {
                if (n % j == 0) {
                    found = false;
                    break;
                }
            }
            return found;
    }

    @Override
    public boolean hasNext() {
        boolean isTrue = false;
        for (int i = index; i < array.length; i++) {
           if (isPrime(array[i]) && i != 1) {
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
            if (isPrime(array[index]) && array[index] != 1) {
                rst = array[index++];
                break;
            }
            index++;
        }
        return rst;
    }
}
