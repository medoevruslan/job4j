package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Bank {
    private Map<User, List<Account>> clients = new HashMap<>();

    public void addUser(User user) {
        List<Account> result = clients.putIfAbsent(user, user.getAccounts());
        if (result != null) {
            System.out.printf("%s already has an account %s", user, result);
        } else {
            System.out.println("Add new client");
        }
    }

    public void deleteUser(User user) {
        for (User client : clients.keySet()) {
            if (user.equals(client)) {
                clients.remove(user);
                break;
            }
        }
    }

    public void addAccountToUser(String passport, Account account) {
        for (User client : clients.keySet()) {
            if (passport.equals(client.getPassport())) {
                client.getAccounts().add(account);
                break;
            }
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        for (User client : clients.keySet()) {
            if (passport.equals(client.getPassport())) {
                client.getAccounts().remove(account);
                break;
            }
        }
    }

    public List<Account> getUserAccounts(String passport) {
        List<Account> list = new ArrayList<>();
        for (User client : clients.keySet()) {
            if (passport.equals(client.getPassport())) {
                list.addAll(client.getAccounts());
            }
        }
        return list;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        int index = -1;
        List<Account> source = this.getUserAccounts(srcPassport);
        List<Account> destination = this.getUserAccounts(dstPassport);
        for (Account acnt : destination) {
            if (dstRequisite.equals(acnt.requisites)) {
                index = destination.indexOf(acnt);
                break;
            }
        }
        if (index != -1) {
            for (Account acnt : source) {
                if (srcRequisite.equals(acnt.requisites)) {
                    result = acnt.transfer(destination, index, amount);
                    break;
                }
            }
        }
        return result;
    }
}
