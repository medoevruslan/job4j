package ru.job4j.servlets;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * Role's model
 */
public class Role {
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
