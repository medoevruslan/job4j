package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class DynamicList<E> implements Iterable<E> {
    private Object[] array;
    private int size = 0;
    private int modCount = 0;

    public DynamicList() {
        this.array = new Object[20];
    }

    private void grow() {
        modCount++;
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity / 2);
        this.array = Arrays.copyOf(this.array, newCapacity);
    }

    public boolean add(E value) {
        if (size >= this.array.length) {
            this.grow();
        }
        this.array[this.size++] = value;
        return true;
    }

    public E get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) this.array[index];
    }

    public E set(int index, E e) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = (E) this.array[index];
        this.array[index] = e;
        return oldValue;
    }

    public int getSize() {
        return this.size;
    }

    public E delete(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        this.modCount++;
        E oldValue = (E) this.array[index];
        int numMoved = this.size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.array, index + 1, this.array, index, numMoved);
        }
        this.array[--this.size] = null;
        return oldValue;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int position = 0;
            private int expectedModCount = modCount;


            @Override
            public boolean hasNext() {
                return position < array.length;
            }

            @Override
            public E next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) array[position++];
            }
        };
    }
}
