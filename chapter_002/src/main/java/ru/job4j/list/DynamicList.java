package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

@ThreadSafe
public class DynamicList<E> implements Iterable<E> {
    private Object[] array;
    @GuardedBy("this")
    private int size = 0;
    private int modCount = 0;
    private final int defaultSize = 20;


    public DynamicList() {
        this.array = new Object[defaultSize];
    }

    public DynamicList(int size) {
        if (size == 0) {
            this.array = new Object[defaultSize];
        } else if (size < 0) {
            throw new IllegalArgumentException(String.format("Illegal size %d", size));
        } else {
            this.array = new Object[size];
        }
    }

    public synchronized boolean addAll(DynamicList<? extends E> input) {
        this.modCount++;
        Object[] arr = input.toArray();
        int newSize = arr.length;
        System.arraycopy(arr, 0, this.array, this.size, newSize);
        return newSize != 0;
    }

    private void grow() {
        this.modCount++;
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity / 2);
        this.array = Arrays.copyOf(this.array, newCapacity);
    }

    public synchronized E[] toArray() {
        return (E[]) this.array;
    }

    public synchronized boolean add(E value) {
        if (size >= this.array.length) {
            this.grow();
        }
        this.modCount++;
        this.array[this.size++] = value;
        return true;
    }

    public synchronized E get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) this.array[index];
    }

    public synchronized E set(int index, E e) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = (E) this.array[index];
        this.array[index] = e;
        return oldValue;
    }

    public synchronized int getSize() {
        return this.size;
    }

    public synchronized E delete(int index) {
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
    public synchronized Iterator<E> iterator() {
        return new Iterator<E>() {
            Object[] copy = this.copy(array);
            private int position = 0;

            private Object[] copy(Object[] input) {
                int len = input.length;
                Object[] rst = new Object[len];
                System.arraycopy(input, 0, rst, 0, len);
                return rst;
            }

            @Override
            public boolean hasNext() {
                return position < this.copy.length;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) this.copy[position++];
            }
        };
    }
}
