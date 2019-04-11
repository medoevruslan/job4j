package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.job4j.storage.model.User;

import java.util.List;

public class UserStorage {

    private final Storage storage;

    public UserStorage(Storage storage) {
        this.storage = storage;
    }

    public int add(User user) {
        return this.storage.add(user);
    }

    public boolean update(User user) {
        return this.storage.update(user);
    }

    public List<User> findAll() {
        return this.storage.findAll();
    }

    @Component("userMemStorage")
    class UserMemoryStorage extends UserStorage {

        public UserMemoryStorage(@Qualifier("memoryStorage") Storage storage) {
            super(storage);
        }
    }

    @Component("userJdbcStorage")
    public static class UserJdbcStorage extends UserStorage {

        public UserJdbcStorage(@Qualifier("jdbcStorage") Storage storage) {
            super(storage);
        }
    }
}
