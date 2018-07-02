package ru.job4j.generic;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class UserStore<T extends User> extends AbstractStore<T> {

    public UserStore(int size) {
       super(size);
    }
}
