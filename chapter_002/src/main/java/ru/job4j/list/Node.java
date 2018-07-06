package ru.job4j.list;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public boolean hasCycle(Node first) {
        boolean isTrue = false;
        if (first.next != null) {
            Node<T> second = first;
            while (second.next != null && second.next.next != null) {
                first = first.next;
                second = second.next.next;
                if (first == second) {
                    isTrue = true;
                    break;
                }
            }
        }
        return isTrue;
    }
}