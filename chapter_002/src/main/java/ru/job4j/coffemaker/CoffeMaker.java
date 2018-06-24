package ru.job4j.coffemaker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class CoffeMaker {

    public int[] changes(int value, int price) {
        List<Integer> list = new ArrayList<>();
        int[] result = null;
        int[] coins = new int[] {10, 5, 2, 1};
        if (value >= price) {
            int change = value - price;
            for (int i = 0; change != 0;) {
                if (change >= coins[i]) {
                    list.add(coins[i]);
                    change -= coins[i];
                } else {
                    i++;
                }
            }
            result = new int[list.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = list.get(i);
            }
        }
        return result;
    }
}
