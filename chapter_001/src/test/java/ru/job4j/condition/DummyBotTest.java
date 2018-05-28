package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class DummyBotTest {
    @Test
    public void whenGreetBot() {
        DummyBot dummyBot = new DummyBot();
        assertThat(dummyBot.answer("Привет, Бот"), is("Привет, умник."));
    }

    @Test
    public void whenByuBot() {
        DummyBot dummyBot = new DummyBot();
        assertThat(dummyBot.answer("Пока"), is("До скорой встречи."));
    }

    @Test
    public void whenUknownBot() {
        DummyBot dummyBot = new DummyBot();
        assertThat(dummyBot.answer("Сколько тебе лет?"), is("Это ставит меня в тупик. Спросите другой вопрос."));
    }

}