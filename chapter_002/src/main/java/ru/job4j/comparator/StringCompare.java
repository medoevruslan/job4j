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
        int length = left.length() - right.length();
        int result = 0;
        if (length == 0) {
            for (int indx = 0; indx < left.length(); indx++) {
                result += left.charAt(indx) - right.charAt(indx);
            }
        }
        return length == 0 ? result : length;
    }
}
