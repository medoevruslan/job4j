package ru.job4j.servlets;

/**
 * City model.
 */
public class City {
    private String name;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
