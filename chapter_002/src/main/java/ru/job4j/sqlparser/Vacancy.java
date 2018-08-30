package ru.job4j.sqlparser;

import java.time.LocalDateTime;

public class Vacancy {
    private String author;
    private String name;
    private int views;
    private LocalDateTime vacansyDate;

    public Vacancy(String name, String author, int views, LocalDateTime date) {
        this.name = name;
        this.author = author;
        this.views = views;
        this.vacansyDate = date;
    }

    public String getAutor() {
        return this.author;
    }

    public String getName() {
        return this.name;
    }

    public int getViews() {
        return this.views;
    }

    public LocalDateTime getVacansyDate() {
        return this.vacansyDate;
    }
}
