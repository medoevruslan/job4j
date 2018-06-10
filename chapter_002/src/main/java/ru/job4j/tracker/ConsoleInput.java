package ru.job4j.tracker;

import java.util.Scanner;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class ConsoleInput implements Input {
    public String ask(String question) {
        System.out.print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int ask(String question, UserAction[] actions ) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (UserAction action : actions) {
            if (key == action.key()) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new  MenuOutException("Out of menu range");
        }
    }
}
