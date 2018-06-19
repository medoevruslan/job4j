package ru.job4j.coffemaker;

import java.util.Arrays;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class CoffeMaker {

    private int[] changes(int value, int price) {
        int[] coins = new int[] {10, 5, 2, 1};
        int[] rst = null;
        int rstIndx = 0;
        if (value >= price) {
            int change = value - price;
            rstIndx = change / 10 + 3;
            rst = new int[rstIndx];
            rstIndx = 0;
            for (int i = 0; change != 0;) {
                if (change >= coins[i]) {
                    rst[rstIndx++] = coins[i];
                    change -= coins[i];
                } else {
                    i++;
                }
            }
        }
        return Arrays.copyOf(rst, rstIndx);
    }
}
