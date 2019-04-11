package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.job4j.storage.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

@Component("memoryStorage")
public class MemoryStorage implements Storage<User> {
    private final List<User> list = new CopyOnWriteArrayList<>();
    @Value("0")
    private int index;

    public MemoryStorage() {
    }

    @Override
    public int add(User model) {
        model.setId(this.index++);
        this.list.add(model);
        return model.getId();
    }

    @Override
    public boolean update(User model) {
        final boolean[] result = {false};
        IntStream.range(0, this.list.size())
                .filter(idx -> this.list.get(idx).getId() == model.getId())
                .findFirst().ifPresent(idx -> {
                    result[0] = true;
                    this.list.set(idx, model);
        });
        return result[0];
    }

    @Override
    public boolean delete(User model) {
        return this.list.remove(model);
    }

    @Override
    public List<User> findAll() {
        return this.list;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
