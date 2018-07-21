package ru.job4j.infostore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class Store {

    public String diff(List<User> previous, List<User> current) {
        int changed = 0;
        ArrayList<User> added = new ArrayList<>(current);
        ArrayList<User> removed = new ArrayList<>(previous);
        added.removeAll(previous);
        removed.removeAll(current);
        for (User user : current) {
            if (previous.contains(user)) {
                if (!previous.get(previous.indexOf(user)).name.equals(user.name)) {
                    changed++;
                }
            }
        }
        return String.format("Added users - %d, changed - %d, removed users - %d", added.size(), changed, removed.size());
    }

    static class User {
        int id;
        String name;

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