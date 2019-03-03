package ru.job4j.carstore.controller;

import ru.job4j.carstore.StaticMapper;
import ru.job4j.carstore.entity.User;
import ru.job4j.carstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Controller manages process of new user creating.
 */
public class UserCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/NewItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String result;
        while ((result = reader.readLine()) != null) {
            builder.append(result);
        }
        result = builder.toString();
        User user = StaticMapper.getInstance().readValue(result, User.class);
        UserService.getInstance().add(user);
        req.getSession().setAttribute("user", user);
    }
}
