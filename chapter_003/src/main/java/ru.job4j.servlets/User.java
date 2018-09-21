package ru.job4j.servlets;

import java.time.LocalDateTime;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String login;
    private String password;
    private LocalDateTime createDate;
    private Role role;
    private Country country;
    private City city;

    public User() { }

    public User(String name, String email, String login, String password) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.createDate = LocalDateTime.now();
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

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role.getName();
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public String getCountry() {
        return this.country.getName();
    }

    public String getCity() {
        return this.city.getName();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateDate(LocalDateTime date) {
        this.createDate = date;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
