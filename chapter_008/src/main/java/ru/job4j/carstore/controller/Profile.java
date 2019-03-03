package ru.job4j.carstore.controller;

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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Profile controller.
 */
public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        StaticMapper mapper = StaticMapper.getInstance();
        mapper.registerModule(new JavaTimeModule());
        List<Item> result = new ArrayList<>();
        for (Item item : ItemService.getInstance().findAll()) {
            if (item.getUser().equals(user)) {
                item.setUser(user);
                item.setCar((Car) Hibernate.unproxy(item.getCar()));
                item.setImages((List<Image>) Hibernate.unproxy(item.getImages()));
                result.add(item);
            }
        }
        PrintWriter writer = resp.getWriter();
        writer.write(mapper.writeValueAsString(result));
        writer.flush();
    }
}
