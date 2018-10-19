package ru.job4j.meloman.controller;

import ru.job4j.meloman.entity.User;
import ru.job4j.meloman.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A controller which forwards to User's page.
 */

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = ((User) req.getSession().getAttribute("user")).getId();
        req.setAttribute("user", UserService.getInstance().findById(id));
        req.getRequestDispatcher("WEB-INF/views/UserView.jsp").forward(req, resp);

    }
}
