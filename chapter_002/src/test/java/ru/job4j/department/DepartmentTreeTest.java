package ru.job4j.department;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

import static org.junit.Assert.assertEquals;

public class DepartmentTreeTest {
    private final String[] input = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                                    "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1",
                                    "K2\\SK1\\SSK2"};
    private final DepartmentTree departments = new DepartmentTree();

    @Test
    public void whenAscendingSortThan() {
        List<String> result = departments.ascendSortDepartments(input);
        List<String> expected = Arrays.asList(
                "K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2",
                "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"
        );
        assertEquals(result, expected);
    }

    @Test
    public void whenDescendingSortThan() {
        List<String> result = departments.descendSortDepartments(input);
        List<String> expected = Arrays.asList(
                "K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"
        );
        assertEquals(result, expected);
    }
}