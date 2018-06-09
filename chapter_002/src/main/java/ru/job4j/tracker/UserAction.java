package ru.job4j.tracker;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public interface UserAction {
    int key();
    void execute(Input input, Tracker tracker);
    String info();
}
