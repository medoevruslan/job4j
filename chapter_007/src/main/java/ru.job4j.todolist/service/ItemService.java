package ru.job4j.todolist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.persistent.ItemRepository;
import ru.job4j.todolist.persistent.implementation.ItemManager;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Supplier;

/**
 * Service which manages the Item entity.
 */
public class ItemService implements ItemRepository {
    private final static ItemService INSTANCE = new ItemService();
    private final ItemManager manager = new ItemManager();

    private ItemService() { }

    public static ItemService getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Item item) {
        this.manager.add(item);
    }

    @Override
    public void update(Item item) {
        this.manager.update(item);
    }

    @Override
    public void delete(Item item) {
        this.manager.delete(item);
    }

    @Override
    public List<Item> findAll() {
        return this.manager.findAll();
    }

    @Override
    public List<Item> findUndone() {
        return this.manager.findUndone();
    }

    public void findItems(HttpServletResponse resp, Supplier<List<Item>> supplier) throws IOException {
        resp.setContentType("json");
        PrintWriter writer = resp.getWriter();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);
        List<Item> items = supplier.get();
        String rst = mapper.writeValueAsString(items);
        writer.write(rst);
        writer.flush();
    }
}
