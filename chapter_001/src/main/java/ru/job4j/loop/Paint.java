package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Paint {

    /**
     * Метод построения пирамиды в псевографике.
     * @param height Высота пирамиды.
     * @return Отображение пирамиды в псевлографике.
     */

    private String loopBy(int height, int width, BiPredicate<Integer, Integer> biPredicate) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (biPredicate.test(row, column)) {
                    stringBuilder.append("^");
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public String pyramid(int height) {
        return this.loopBy(
                height,
                height * 2 - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }
}