package ru.job4j.threads;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class BaseStore {
    private ConcurrentHashMap<Integer, Base> store;

    public BaseStore() {
        this.store = new ConcurrentHashMap<>();
    }

    public boolean add(Base model) {
        this.store.put(model.getId(), model);
        return true;
    }

    public boolean update(Base model) throws OptimisticException {
        int ver = model.getVersion();
        store.computeIfPresent(model.getId(), (key, value) -> {
            if (ver != model.getVersion()) {
                throw new OptimisticException();
            }
            model.setName("AnotherName");
            return value;
        });
        model.setVersion(ver + 1);
        return true;
    }

    public boolean delete(Base model) {
        return store.computeIfPresent(model.getId(), (k, v) -> store.remove(model)) != null;
    }
}
