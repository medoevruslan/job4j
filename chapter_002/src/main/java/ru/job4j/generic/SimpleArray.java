package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int index = 0;
    private int position = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public boolean add(T model) {
        this.rangeCheck(index);
        this.array[index++] = model;
        return true;
    }

    public T set(int index, T model) {
        this.rangeCheck(index);
        T oldValue = (T) this.array[index];
        this.array[index] = model;
        return oldValue;
    }

    public void delete(int index) {
        this.rangeCheck(index);
        this.array[index] = null;
        if (index != array.length -1) {
            System.arraycopy(this.array, index + 1, this.array, index, this.array.length - 1);
        } else {
            System.arraycopy(this.array, index, this.array, index, array.length - 1);
        }
    }

    public T get(int index) {
        this.rangeCheck(index);
        return (T) this.array[index];
    }

    private void rangeCheck(int index) {
        if (index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (array.length == 0) {
                    throw new NoSuchElementException();
                }
                return position < array.length;
            }

            @Override
            public T next() {
                if (position == array.length) {
                    throw new NoSuchElementException();
                }
                return (T) array[position++];
            }
        };
    }
}