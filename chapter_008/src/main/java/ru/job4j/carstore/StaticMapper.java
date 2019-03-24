package ru.job4j.carstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.Hibernate;
import ru.job4j.carstore.entity.Car;
import ru.job4j.carstore.entity.Image;
import ru.job4j.carstore.entity.Item;
import ru.job4j.carstore.entity.User;

import java.util.List;

/**
 * ObjectMapper singleton.
 */
public class StaticMapper extends ObjectMapper {
    private static final StaticMapper INSTANCE = new StaticMapper();

    private StaticMapper() { }

    public static StaticMapper getInstance() {
        return INSTANCE;
    }

    public String unproxyAndJson(List<Item> items) throws JsonProcessingException {
        items.forEach(item -> {
            item.setUser((User) Hibernate.unproxy(item.getUser()));
            item.setCar((Car) Hibernate.unproxy(item.getCar()));
            item.setImages((List<Image>) Hibernate.unproxy(item.getImages()));
        });
        this.registerModule(new JavaTimeModule());
        return this.writeValueAsString(items);
    }

}
