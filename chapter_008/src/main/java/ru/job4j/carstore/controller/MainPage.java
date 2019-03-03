package ru.job4j.carstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.Hibernate;
import ru.job4j.carstore.StaticMapper;
import ru.job4j.carstore.entity.Car;
import ru.job4j.carstore.entity.Image;
import ru.job4j.carstore.entity.Item;
import ru.job4j.carstore.entity.User;
import ru.job4j.carstore.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller loads index page and pass data to fill in table with items.
 */
public class MainPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("items", this.createJsonItems());
        req.getRequestDispatcher("/WEB-INF/views/Index.jsp").forward(req, resp);
    }

    private String createJsonItems() throws JsonProcessingException {
        StaticMapper mapper = StaticMapper.getInstance();
        mapper.registerModule(new JavaTimeModule());
        List<Item> items = ItemService.getInstance().findAll();
        for (Item item : items) {
            item.setUser((User) Hibernate.unproxy(item.getUser()));
            item.setCar((Car) Hibernate.unproxy(item.getCar()));
            item.setImages((List<Image>) Hibernate.unproxy(item.getImages()));
        }
        return mapper.writeValueAsString(items);
    }
}
