package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class DynamicLinkedList<E> implements Iterable<E> {
    private Node<E> first;
    private int size = 0;
    private int modCount;


    public void add(E data) {
        this.modCount++;
        Node<E> newNode = new Node<>(data);
        newNode.next = this.first;
        this.first = newNode;
        this.size++;
    }

    public E delete() {
        this.modCount++;
        Node<E> result = this.first;
        this.first = result.next;
        E temp = result.data;
        result = null;
        this.size--;
        return temp;
    }

    public E get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> result = this.first;
        for (int indx = 0; indx < index; indx++) {
            result = result.next;
        }
        return (E) result;
    }

    @Override
    public Iterator<E> iterator() {
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

        public Node(E data) {
            this.data = data;
        }
    }
}
