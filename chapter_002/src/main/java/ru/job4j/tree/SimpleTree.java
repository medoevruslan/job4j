package ru.job4j.tree;

import java.util.Optional;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public interface SimpleTree<T extends Comparable<T>> extends Iterable<T> {

    boolean add(T parent, T child);

    Optional<Node<T>> findBy(T value);
}
