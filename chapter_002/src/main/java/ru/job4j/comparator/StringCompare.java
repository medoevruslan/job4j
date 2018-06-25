package ru.job4j.comparator;

import java.util.Comparator;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int lengthl = left.length();
        int lengthr = right.length();
        int lim = Math.min(lengthl, lengthr);
        char[] lefcha = left.toCharArray();
        char[] rigtcha = right.toCharArray();
        int result = 0;
        for (int indx = 0; indx < lim; indx++) {
            if (lefcha[indx] != rigtcha[indx]) {
               result = lefcha[indx] - rigtcha[indx];
               break;
            }
        }
        return result != 0 ? result : lengthl - lengthr;
    }
}
