package ru.job4j.tracker;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

/**
 * Класс для теста корректной работы StartUI.
 */

public class StabInput implements Input {
    private final String[] values;
    private int position = 0;

    public StabInput(final String[] values) {
        this.values = values;
    }

    /**
     * Метод эмуляции пользовательского выбора.
     * @param question Вопрос для пользователя.
     * @return Эмуляция ответа пользователя.
     */

    @Override
    public String ask(String question) {
        return this.values[this.position++];
    }

    public int ask(String question, UserAction[] actions) {
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

