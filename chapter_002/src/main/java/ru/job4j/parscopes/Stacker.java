package ru.job4j.parscopes;

import java.util.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Stacker {

    private final Map<Character, Character> braces = new HashMap<>();
    {
        braces.put('[', ']');
        braces.put('{', '}');
        braces.put('(', ')');
    }

    /**
     * Method check the brackets and add them to list.
     * @param first  bracket to check.
     * @param start  starting position of bracket.
     * @param second bracket to check
     * @param end end position of bracket
     * @param list list where are brackets stroring.
     * @return true or false of operation.
     */

    private boolean isValidMatch(char first, int start, char second, int end, ArrayList<Brackets> list) {
        boolean result = false;
        if (first == '(' && second == ')') {
            list.add(new Parentheses(start, end));
            result = true;
        } else if (first == '{' && second == '}') {
            list.add(new Braces(start, end));
            result = true;
        } else if (first == '[' && second == ']') {
            list.add(new SquareScopes(start, end));
            result = true;
        }
        return result;
    }

    /**
     * Method check balance in a expression.
     * @param input String to parse.
     * @param list list where are brackets stroring.
     * @return true or false of operation.
     */

    boolean checkAndParse(String input, ArrayList<Brackets> list) {
        char[] bra = input.toCharArray();
        Stack<Character> stackBra = new Stack<>();
        Stack<Integer> stackInd = new Stack<>();
        for (int i = 0; i < bra.length; i++) {
            if (braces.containsKey(bra[i])) {
                stackBra.push(bra[i]);
                stackInd.push(i);
            } else if (braces.containsValue(bra[i])) {
                if (!stackBra.empty()) {
                    if (!isValidMatch(stackBra.pop(), stackInd.pop(), bra[i], i, list)) {
                        return false;
                    }
                }
            }
        }
        return stackBra.empty();
    }
}
