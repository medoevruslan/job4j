package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<T extends Comparable<T>> implements Comparable<T> {
    private final List<Node<T>> children = new ArrayList<>();
    private final T value;

    public Node(final T value) {
        this.value = value;
    }

    public void add(Node<T> child) {
        this.children.add(child);
    }

    public List<Node<T>> leaves() {
        return this.children;
    }

    public boolean eqValue(T that) {
        return this.value.compareTo(that) == 0;
    }

    @Override
    public int compareTo(T o) {
        return this.compareTo(o);
    }

    @Override
    public int hashCode() {
        int result = 17;
        return result * 31 + (this.value == null ? 0 : this.value.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        boolean rst = false;
        if (o != null && getClass() == this.getClass()) {
            if (o == this) {
                rst = true;
            } else {
                rst = this.eqValue((T)o);
            }
        }
        return rst;
    }
}
