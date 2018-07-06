package ru.job4j.set;

import ru.job4j.list.DynamicLinkedList;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleNodeSet<E> {
    private DynamicLinkedList<E> list;

    public SimpleNodeSet() {
        this.list = new DynamicLinkedList<>();
    }

    private boolean isExist(E e) {
        boolean isExist = false;
        for (int indx = 0; indx < this.list.getSize(); indx++) {
            if (list.get(indx).equals(e)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public void add(E e) {
        if (this.list.getSize() != 0) {
            if(!isExist(e)) {
                this.list.add(e);
            }
        } else {
            this.list.add(e);
        }
    }

    public Iterator<E> getIterator() {
        return this.list.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleNodeSet<?> that = (SimpleNodeSet<?>) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
