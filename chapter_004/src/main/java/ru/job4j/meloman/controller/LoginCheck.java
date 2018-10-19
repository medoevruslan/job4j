package ru.job4j.meloman.controller;

import ru.job4j.meloman.entity.User;
import ru.job4j.meloman.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller that checks if login exists.
 */

public class LoginCheck extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/plain");
        for (User user : UserService.getInstance().findAll()) {
            if (login.equals(user.getLogin())) {
                writer.write("exist");
                writer.flush();
                break;
            }
        }
    }
}
