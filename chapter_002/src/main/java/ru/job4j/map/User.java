package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return this.children == user.children && Objects.equals(this.name, user.name);
    }
}
