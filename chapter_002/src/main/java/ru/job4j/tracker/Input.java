package ru.job4j.tracker;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public interface Input {
    String ask(String question);

    int ask(String question, UserAction[] actions);
}
