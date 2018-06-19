package ru.job4j.parscopes;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Stacker {

    /**
     * Method check the brackets and add them to list.
     * @param first  bracket to check.
     * @param start  starting position of bracket.
     * @param second bracket to check
     * @param end end position of bracket
     * @param list list where are brackets stroring.
     * @return true or false of operation.
     */

    private boolean isValidMatch(char first, int start, char second, int end, LinkedList<Brackets> list) {
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

    boolean checkAndParse(String input, LinkedList<Brackets> list) {
        char[] bra = input.toCharArray();
        Stack<Character> stackBra = new Stack<>();
        Stack<Integer> stackInd = new Stack<>();
        for (int i = 0; i < bra.length; i++) {
            if (bra[i] == '(' || bra[i] == '{' || bra[i] == '[') {
                stackBra.push(bra[i]);
                stackInd.push(i);
            } else if (bra[i] == ')' || bra[i] == '}' || bra[i] == ']') {
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
