package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class SortUser {
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }
}
