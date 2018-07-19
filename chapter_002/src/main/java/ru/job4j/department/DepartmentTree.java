package ru.job4j.department;

import java.util.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class DepartmentTree {

    public List<String> ascendSortDepartments(String[] input) {
        Set<String> set = new TreeSet<>();
        for (String department : input) {
            if (department.contains("\\")) {
                set.add(department.substring(0, department.lastIndexOf("\\")));
            }
            set.add(department);
        }
        return new ArrayList<>(set);
    }

    public List<String> descendSortDepartments(String[] input) {
        List<String> list = this.ascendSortDepartments(input);
        Comparator<String> descend = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int rst = -o1.compareTo(o2);
                return o1.indexOf(o2) == 0 ? 1 : rst;
            }
        };
        list.sort(descend);
        return list;
    }
}
