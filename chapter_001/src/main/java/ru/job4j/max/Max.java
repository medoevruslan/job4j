package ru.job4j.max;

public class Max {

    /**
     * Метод вычисляет максимум из двух чисел.
     * @param first первое число.
     * @param second второе число.
     * @return наибольшее из двух чисел.
     */

    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Метод вычисляет максимум из трех чисел.
     * @param first первое число.
     * @param second  второе число.
     * @param third третье число.
     * @return наибольшее из трех чисел.
     */

    public int max(int first, int second, int third) {
        return max(first, max(second, third));
    }
}
