package ru.job4j.servlets;

/**
 * Country model.
 */
public class Country {
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
