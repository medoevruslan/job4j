package ru.job4j.tree;

import java.util.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class Tree<T extends Comparable<T>> implements SimpleTree<T> {
    private Node<T> root;
    private int size = 0;
    private int modCount = 0;

    public Tree(T value) {
        this.root = new Node<>(value);
        this.modCount++;
        this.size++;
    }

    private boolean canToAdd(Optional<Node<T>> parent, T child) {
        boolean canTo = parent.isPresent();
        if (canTo) {
            for (Node el : parent.get().leaves()) {
                if (el.eqValue(child)) {
                    canTo = false;
                    break;
                }
            }
        }
        return canTo;
    }


    public boolean add(T parent, T child) {
        boolean rst = false;
        Optional<Node<T>> root = this.findBy(parent);
        if (this.canToAdd(root, child)) {
            Node<T> chld = new Node<>(child);
            root.get().add(chld);
            rst = true;
            this.size++;
            this.modCount++;
        }
        return rst;
    }

    @Override
    public Optional<Node<T>> findBy(T value) {
        Optional<Node<T>> rst = Optional.empty();
        Queue<Node<T>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<T> el = data.poll();
            if (el.eqValue(value)) {
                rst = Optional.of(el);
                break;
            }
            for (Node<T> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rst;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Queue<Node<T>> data = new LinkedList<>();
            private int expectedModCount = modCount;
            private int position = 0;
            Node<T> current = root;

            @Override
            public boolean hasNext() {
                return this.position < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (position == 0) {
                    this.data.offer(current);
                }
                    this.current = this.data.poll();
                    if (this.current.leaves().size() != 0) {
                        for (Node n : current.leaves()) {
                            this.data.offer(n);
                        }
                    }
                    this.position++;
                return this.current.getValue();
            }
        };
    }
}
