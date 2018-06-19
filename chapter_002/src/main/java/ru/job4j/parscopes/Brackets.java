package ru.job4j.parscopes;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public abstract class Brackets {
    final int start;
    final int end;

    Brackets(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public abstract String getKind();

}
