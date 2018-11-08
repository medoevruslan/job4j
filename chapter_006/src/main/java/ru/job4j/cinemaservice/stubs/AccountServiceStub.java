package ru.job4j.cinemaservice.stubs;

import ru.job4j.cinemaservice.model.Account;
import ru.job4j.cinemaservice.persistent.AccountDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * Stub provides us to make the tests.
 */
public class AccountServiceStub implements AccountDAO {
    private final ArrayList<Account> accounts = new ArrayList<>();

    @Override
    public int add(Account model) {
        this.accounts.add(model);
        return model.getId();
    }

    @Override
    public boolean update(Account model) {
        AtomicBoolean result = new AtomicBoolean(false);
        IntStream.range(0, this.accounts.size())
                .filter(idx -> model.getId() == this.accounts.get(idx).getId())
                .findFirst().ifPresent(idx -> {
                    result.set(true);
                    model.setId(this.accounts.get(idx).getId());
                    this.accounts.set(idx, model);
        });
        return result.get();
    }

    @Override
    public boolean remove(Account model) {
        return this.accounts.remove(model);
    }

    @Override
    public Optional<Account> findById(int id) {
        return this.accounts.stream()
                .filter(a -> id == a.getId())
                .findFirst();
    }

    @Override
    public List<Account> findAll() {
        return this.accounts;
    }
}
