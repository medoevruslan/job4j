package ru.job4j.professions;

public class Profession {
    private String name;
    public String profession;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public String getProfession() {
        this.profession = this.getClass().getSimpleName();
        return profession;
    }
}

