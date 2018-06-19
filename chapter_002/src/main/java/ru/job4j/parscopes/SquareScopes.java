package ru.job4j.parscopes;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class SquareScopes extends Braces {

    SquareScopes(int start, int end) {
        super(start, end);
    }

    @Override
    public String getKind() {
        return "[]";
    }
}
