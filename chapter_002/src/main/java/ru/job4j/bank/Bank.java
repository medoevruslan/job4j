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

    public Account getAccount(String passport, String requisit) {
        Account account = null;
        List<Account> list = this.getUserAccounts(passport);
        for (Account acnt : list) {
            if (requisit.equals(acnt.requisites)) {
                account = acnt;
            }
        }
        return account;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account source = getAccount(srcPassport, srcRequisite);
        Account destination = getAccount(dstPassport, dstRequisite);
        if (source != null && destination != null) {
            result = source.transfer(destination, amount);
        }
        return result;
    }
}
