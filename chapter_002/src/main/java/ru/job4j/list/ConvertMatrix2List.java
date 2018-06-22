package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] row : array) {
            for (int col : row) {
                result.add(col);
            }
        }
        return result;
    }
}
