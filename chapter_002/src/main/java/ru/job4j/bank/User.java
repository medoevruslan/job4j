package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class User {
    private String name;
    private String passport;
    private List<Account> accounts = new ArrayList<>();

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return this.name;
    }

    public String getPassport() {
        return this.passport;
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }


}
