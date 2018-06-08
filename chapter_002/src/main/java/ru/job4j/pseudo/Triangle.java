package ru.job4j.pseudo;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class Triangle implements Shape {
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("   ^   ").append(System.lineSeparator());
        pic.append("  ^^^  ").append(System.lineSeparator());
        pic.append(" ^^^^^ ").append(System.lineSeparator());
        pic.append("^^^^^^^");
        return pic.toString();
    }
}
