package ru.job4j.infostore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class Store {
    private int add = 0;
    private int rem = 0;
    private int changed = 0;

    public String diff(List<User> previous, List<User> current) {
        HashSet<User> added = new HashSet<>(current);
        HashSet<User> removed = new HashSet<>(previous);
        added.removeAll(previous);
        removed.removeAll(current);
        for (User user : current) {
            if (previous.contains(user)) {
                if (!previous.get(previous.indexOf(user)).name.equals(user.name)) {
                    changed++;
                }
            }
        }
        this.add = added.size();
        this.rem = removed.size();
        return String.format("Added users - %d, changed - %d, removed users - %d", added.size(), changed, removed.size());
    }

    public int getAdd() {
        return this.add;
    }

    public int getRem() {
        return this.rem;
    }

    public int getChanged() {
        return this.changed;
    }

    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            boolean rst = false;
            if (this == o) {
                rst = true;
            } else if (o != null && getClass() == o.getClass()) {
                User user = (User) o;
                rst = (id == user.id);
            }
            return rst;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + this.id;
            return result;
        }
    }
}