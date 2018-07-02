package ru.job4j.generic;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class RoleStore<T extends Role> extends AbstractStore<T> {

    public RoleStore(int size) {
        super(size);
    }
}
