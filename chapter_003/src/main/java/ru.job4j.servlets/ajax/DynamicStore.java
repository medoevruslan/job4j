package ru.job4j.servlets.ajax;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * Store for working with UserController.class.
 */
public class DynamicStore {
    private static final DynamicStore INSTANCE = new DynamicStore();
    private DynamicStore() { };
    private ConcurrentHashMap<Integer, JsonUser> map = new ConcurrentHashMap<>();
    private int id = 0;

    public static DynamicStore getInstance() {
        return INSTANCE;
    }

    public boolean add(JsonUser user) {
        user.setId(String.valueOf(this.id));
        this.map.put(this.id++, user);
        return true;
    }

    public boolean delete(int id) {
        boolean rst = false;
        if (this.map.containsKey(id)) {
            rst = true;
            this.map.remove(id);
        }
        return rst;
    }

    public JsonUser findById(int id) {
        return this.map.get(id);
    }

    public ConcurrentHashMap<Integer, JsonUser> findAll() {
        return this.map;
    }
}
