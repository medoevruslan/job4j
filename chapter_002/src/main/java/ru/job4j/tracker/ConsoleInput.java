package ru.job4j.tracker;

import java.util.List;
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

    private boolean isValidKey(int key, List<UserAction> actions) {
        boolean valid = false;
        for (UserAction action : actions) {
            if (key == action.key()) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    public int ask(String question, List<UserAction> actions) {
       int key = Integer.valueOf(this.ask(question));
       if (!isValidKey(key, actions)) {
           throw new MenuOutException("Out of menu range");
       }
        return key;
    }
}

