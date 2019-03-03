package ru.job4j.carstore.controller;

import ru.job4j.carstore.entity.User;
import ru.job4j.carstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Sign in controller.
 */
public class SignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean exist = false;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        List<User> users = UserService.getInstance().findAll();
        for (User usr: users) {
            if (usr.getLogin().equals(login) && usr.getPassword().equals(password)) {
                req.getSession().setAttribute("user", usr);
                exist = true;
            }
        }
        if (!exist) {
            PrintWriter writer = resp.getWriter();
            writer.write("false");
            writer.flush();
        }
    }
}
