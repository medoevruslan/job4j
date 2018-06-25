package ru.job4j.bank;

import java.util.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Account {
    double value;
    String requisites;

    public Account(String requisites, double value) {
        this.requisites = requisites;
        this.value = value;
    }

    public boolean transfer(Account dest, double amount) {
        boolean result = false;
        if (amount <= this.value && amount > 0) {
            result = true;
            this.value -= amount;
            dest.value += amount;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.value, value) == 0
                && Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, requisites);
    }
}

