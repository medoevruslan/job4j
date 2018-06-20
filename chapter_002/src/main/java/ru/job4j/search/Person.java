package ru.job4j.search;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Person {
    private String name;
    private String surname;
    private String phone;
    private String adress;

    public Person(String name, String surname, String phone, String adress) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }
}
