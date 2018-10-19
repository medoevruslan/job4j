package ru.job4j.meloman.controller;

import ru.job4j.meloman.dao.UserDAO;
import ru.job4j.meloman.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A controller which removes the user from a database.
 */

public class UserDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        UserDAO service = UserService.getInstance();
        int id = Integer.parseInt(req.getParameter("id"));
        service.remove(service.findById(id));

    }
}
