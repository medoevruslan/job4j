package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : this.persons) {
            if (person.getName().contains(key)) {
                result.add(person);
            } else if (person.getSurname().contains(key)) {
                result.add(person);
            } else if (person.getPhone().contains(key)) {
                result.add(person);
            } else if (person.getAdress().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
