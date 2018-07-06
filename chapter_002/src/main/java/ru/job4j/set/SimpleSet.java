package ru.job4j.set;

import ru.job4j.list.DynamicList;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleSet<E> {
    private DynamicList<E> list;

    public SimpleSet() {
        this.list = new DynamicList<>();
    }

    public void add(E e) {
        if (this.list.getSize() != 0) {
            if (!this.isExist(e)) {
                this.list.add(e);
            }
        } else {
            this.list.add(e);
        }
    }

    private boolean isExist(E e) {
        boolean isExist = false;
        for (int indx = 0; indx < this.list.getSize(); indx++) {
            if (this.list.get(indx).equals(e)) {
                this.list.set(indx, e);
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public int getSize() {
        return this.list.getSize();
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
        SimpleSet<?> simpleSet = (SimpleSet<?>) o;
        return Objects.equals(list, simpleSet.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
