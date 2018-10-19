package ru.job4j.meloman.controller;

import ru.job4j.meloman.dao.UserDAO;
import ru.job4j.meloman.entity.User;
import ru.job4j.meloman.service.UserService;
import ru.job4j.meloman.service.Utils;
import ru.job4j.meloman.service.ViewSelector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A controller that forwards to appropriate edit's page and invokes update method.
 */

public class UserUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO service = UserService.getInstance();
        int id = Integer.valueOf(req.getParameter("id"));
        User user = service.findById(id);
        req.setAttribute("user", user);
        req.setAttribute("address", service.getAddress(user));
        req.getRequestDispatcher(ViewSelector.selector().selectView(req)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = Utils.getUtil().convertJson(req, User.class);
        UserService.getInstance().update(user);
    }
}
