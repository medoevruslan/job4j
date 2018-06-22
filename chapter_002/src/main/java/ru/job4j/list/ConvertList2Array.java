package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class ConvertList2Array {

    public int[][] toArray(List<Integer> list, int rows) {
        int cols = (list.size() + rows - 1) / rows;
        int[][] array = new int[rows][cols];
        int col = 0;
        rows = 0;
        for (int a:list) {
            if (col == cols) {
                rows++;
                col = 0;
            }
            array[rows][col++] = a;
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] index : list) {
            for (int element : index) {
                result.add(element);
            }
        }
        return result;
    }
}

