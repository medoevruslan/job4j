package ru.job4j.generic;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public abstract class Base {
    private final String id;

    protected Base(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
