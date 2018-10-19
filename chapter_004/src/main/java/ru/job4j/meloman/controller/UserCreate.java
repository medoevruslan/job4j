package ru.job4j.meloman.controller;

import ru.job4j.meloman.entity.User;
import ru.job4j.meloman.service.UserService;
import ru.job4j.meloman.service.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A controller that forwards to user's create page and invokes the "addFullUser()" method.
 */
public class UserCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/CreateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = Utils.getUtil().convertJson(req, User.class);
        UserService.getInstance().addFullUser(user);
    }
}
