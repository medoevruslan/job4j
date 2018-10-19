package ru.job4j.meloman.controller;

import ru.job4j.meloman.entity.User;
import ru.job4j.meloman.service.UserService;
import ru.job4j.meloman.service.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller which forwards to login's page and adds the user to the session.
 */

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String users = Utils.MAPPER.writeValueAsString(UserService.getInstance().getAllEntities());
        req.setAttribute("users", users);
        req.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = Utils.getUtil().convertJson(req, User.class);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
    }
}
