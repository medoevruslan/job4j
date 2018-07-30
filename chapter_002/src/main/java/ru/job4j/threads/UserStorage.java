package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

@ThreadSafe
public class UserStorage {
    private final ArrayList<User> list = new ArrayList<>();

    @GuardedBy("this")
    public synchronized boolean add(User user) {
        return this.list.add(user);
    }

    @GuardedBy("this")
    public synchronized boolean delete(User user) {
        return this.list.remove(user);
    }

    @GuardedBy("this")
    public synchronized boolean update(User user) {
        return true;
    }

    @GuardedBy("this")
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rst = false;
        User from = this.getUser(fromId);
        User to = this.getUser(toId);
        if (from != null && to != null && from.amount >= amount) {
                from.amount -= amount;
                to.amount += amount;
                rst = true;
        }
        return rst;
    }

    private User getUser(int id) {
        User rst = null;
        for (User user : list) {
            if (user.id == id) {
                rst = user;
                break;
            }
        }
        return rst;
    }

    private class User {
        private int id;
        private int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            boolean rst = false;
           if (this == o) {
               rst = true;
           } else if (o != null && getClass() == o.getClass()) {
               User user = (User) o;
               rst = this.id == user.id;
           }
            return rst;
        }

        @Override
        public int hashCode() {
            int result = 17;
            return result * 31 + this.id;
        }
    }
}
