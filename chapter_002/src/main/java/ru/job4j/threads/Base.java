package ru.job4j.threads;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Base {
    private String name;
    private int id;
    private volatile int version;

    public Base() {
        this.id = (int) (Math.random() * 100);
        this.version = 0;
        this.name = "Test";
    }

    public int getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }
}