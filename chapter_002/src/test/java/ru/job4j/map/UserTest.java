package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class UserTest {

    Map<User, Object> map;

    @Before
    public void initTest() {
        User first = new User("Ruslan", 0);
        User second = new User("Ruslan", 0);
        map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
    }

    @Test
    public void whenHashCodeAndEqualsMethodsWereNotOverridenThanMapHas() {
        System.out.println(map);
    }

    @Test
    public void whenOnlyHashCodeOverridenButEqualsNo() {
        System.out.println(map);
    }

    @Test
    public void whenOnlyEqualsOverridenButHashCodeNo() {
        System.out.println(map);
    }

    @Test
    public void whenEqualsAndHashCodeWereOverriden() {
        System.out.println(map);
    }
}