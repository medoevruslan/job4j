package ru.job4j.loop;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Paint {

    /**
     * Метод посроения пирамиды в псевографике.
     * @param height Высота пирамиды.
     * @return Отображение пирамиды в псевлографике.
     */

    public String pyramid(int height) {
        StringBuilder stringBuilder = new StringBuilder();
        int width = height * 2 - 1;
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    stringBuilder.append("^");
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}