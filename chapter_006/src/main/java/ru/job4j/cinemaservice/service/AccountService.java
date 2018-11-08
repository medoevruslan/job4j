package ru.job4j.cinemaservice.service;

import ru.job4j.cinemaservice.model.Account;
import ru.job4j.cinemaservice.persistent.AccountDAO;
import ru.job4j.cinemaservice.persistent.dbasedao.AccountManager;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for working with Account DAO
 */
public class AccountService implements AccountDAO {
    private final static AccountService INSTANCE = new AccountService();
    private final AccountManager manager = new AccountManager();

    private AccountService() { }

    public static AccountDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Account model) {
        return this.manager.add(model);
    }

    @Override
    public boolean update(Account model) {
        return this.manager.update(model);
    }

    @Override
    public boolean remove(Account model) {
        return this.manager.remove(model);
    }

    @Override
    public Optional<Account> findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return this.manager.findAll();
    }
}
