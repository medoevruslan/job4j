package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class PhoneDictionaryTest {

    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Ruslan", "Medoev", "277771", "Kiev"));
        List<Person> persones = phones.find("771");
        assertThat(persones.iterator().next().getPhone(), is("277771"));
    }
}
