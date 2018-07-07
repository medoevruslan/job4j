package ru.job4j.map;

import java.util.Calendar;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class User {
    private String name;
    private int children;
    private Calendar day;

    public User(String name, int children) {
        this.name = name;
        this.children = children;
    }
}
