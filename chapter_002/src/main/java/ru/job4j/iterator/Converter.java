package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> iterator;

            private boolean check() {
                if (iterator == null && it.hasNext()) {
                    iterator = it.next();
                }
                return iterator.hasNext();
            }

            @Override
            public boolean hasNext() {
                if (!this.check()) {
                    if (it.hasNext()) {
                        iterator = it.next();
                    }
                }
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (!iterator.hasNext() && it.hasNext()) {
                    iterator = it.next();
                }
                return iterator.next();
            }
        };
    }
}

