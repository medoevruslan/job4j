package ru.job4j.servlets;

import java.time.LocalDateTime;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public class User {
    private static int instanceCount;
    private int id;
    private String name;
    private String email;
    private String login;
    private LocalDateTime createDate;

    public User(String name, String email, String login) {
        instanceCount++;
        this.id = instanceCount;
        this.name = name;
        this.email = email;
        this.login = login;
        this.createDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", login='" + login + '\''
                + ", createDate=" + createDate
                + '}';
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLogin() {
        return this.login;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
