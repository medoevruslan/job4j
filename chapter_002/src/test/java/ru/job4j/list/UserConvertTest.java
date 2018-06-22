package ru.job4j.list;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class UserConvertTest {

    @Test
    public void whenListHas2Users() {
        UserConvert convert = new UserConvert();
        List<User> list = Arrays.asList(
                new User(1, "First"), new User(2, "Second"));
        HashMap<Integer, User> result = convert.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(1, new User(1, "First"));
        expected.put(2, new User(2, "Second"));
        assertThat(result, is(expected));
    }
}
