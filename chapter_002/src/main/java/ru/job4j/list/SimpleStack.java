package ru.job4j.list;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleStack<T> extends DynamicLinkedList<T> {

    public T poll() {
        return deleteLast();
    }

    public void push(T value) {
        add(value);
    }
}
