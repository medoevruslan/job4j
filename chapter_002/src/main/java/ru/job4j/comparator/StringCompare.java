package ru.job4j.comparator;

import java.util.Comparator;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int le = 0;
        int ri = 0;
        for (char ind : left.toCharArray()) {
            le += ind;
        }
        for (char ind : right.toCharArray()) {
            ri += ind;
        }
        return le - ri;
    }
}
