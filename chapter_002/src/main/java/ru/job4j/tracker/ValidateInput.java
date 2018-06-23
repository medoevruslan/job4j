package ru.job4j.tracker;

import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, List<UserAction> actions) {
        int key = -1;
        boolean invalid = true;
        do {
            try {
                key = this.input.ask(question, actions);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please, enter correct number of menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please, select key from menu.");
            }
        } while (invalid);
        return key;
    }
}
