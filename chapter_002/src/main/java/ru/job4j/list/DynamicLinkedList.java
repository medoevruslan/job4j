package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

@ThreadSafe
public class DynamicLinkedList<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    @GuardedBy("this")
    private int size = 0;
    private int modCount;

    public synchronized void add(E data) {
        this.linklLast(data);
    }

    private synchronized void linkFirst(E data) {
        this.modCount++;
        Node<E> f = this.first;
        Node<E> newNode = new Node<>(data);
        newNode.next = f;
        this.first = newNode;
        if (f == null) {
            this.last = newNode;
        } else {
            f.prev = newNode;
        }
        this.size++;
    }

    private synchronized void linklLast(E data) {
        this.modCount++;
        Node<E> l = this.last;
        Node<E> newNode = new Node<>(data);
        newNode.prev = l;
        this.last = newNode;
        if (l == null) {
            this.first = newNode;
        } else {
            l.next = newNode;
        }
        this.size++;
    }

    public synchronized E getFirst() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }
        return this.first.data;
    }

    public synchronized E getLast() {
        if (this.last == null) {
            throw new NoSuchElementException();
        }
        return this.last.data;
    }

    public synchronized E deleteFirst() {
        this.modCount++;
        Node<E> next = this.first.next;
        E data = this.first.data;
        this.first.next = null;
        this.first.data = null;
        this.first = next;
        if (this.first == null) {
            this.last = null;
        } else {
            this.first.prev = null;
        }
        this.size--;
        return data;
    }

    public synchronized E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) this.node(index).data;
    }

    public synchronized E deleteLast() {
        this.modCount++;
        E data = this.last.data;
        Node<E> prev = this.last.prev;
        this.last.prev = null;
        this.last.data = null;
        this.last = prev;
        if (this.last == null) {
            this.first = null;
        } else {
            this.last.next = null;
        }
        this.size--;
        return data;
    }

    private Node<E> node(int index) {
        Node<E> result = this.first;
        if (index < size / 2) {
            for (int indx = 0; indx < index; indx++) {
                result = result.next;
            }
        } else {
            result = this.last;
            for (int indx = this.size - 1; indx > index; indx--) {
                result = result.prev;
            }
        }
        return result;
    }

    public synchronized int getSize() {
        return this.size;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return new Iterator<E>() {
            private int position = 0;
            private  Node<E> pointer = first;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return this.position < size;
            }

            private Node<E> node(int position, Node<E> pointer) {
                if (position != 0) {
                    this.pointer = pointer.next;
                }
                return this.pointer;
            }

            @Override
            public E next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) this.node(position++, this.pointer).data;
            }
        };
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E data) {
            this.data = data;
        }
    }
}
