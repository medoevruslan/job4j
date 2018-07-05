package ru.job4j.list;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleQueue<T> extends DynamicList<T> {

    public T poll() {
        return delete(0);
    }

    public void push(T value) {
        add(value);
    }
}
