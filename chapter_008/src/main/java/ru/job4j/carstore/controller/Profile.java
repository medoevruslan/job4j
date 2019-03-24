package ru.job4j.carstore.controller;

import ru.job4j.carstore.StaticMapper;
import ru.job4j.carstore.entity.Item;
import ru.job4j.carstore.entity.User;
import ru.job4j.carstore.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        List<Item> result = ItemService.getInstance().findByModel(user);
        PrintWriter writer = resp.getWriter();
        writer.write(StaticMapper.getInstance().unproxyAndJson(result));
        writer.flush();
    }
}
