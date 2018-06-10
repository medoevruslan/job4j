package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    public int ask(String question, UserAction[] actions) {
        int key = -1;
        boolean invalid = true;
        do {
            try {
                key = super.ask(question, actions);
                invalid = false;
            }catch (MenuOutException moe) {
                System.out.println("Please, enter correct number of menu.");
            }catch (NumberFormatException nfe) {
                System.out.println("Please, select key from menu.");
            }
        } while (invalid);
        return key;
    }
}
