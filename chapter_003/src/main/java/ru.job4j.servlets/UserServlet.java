package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * User's home page.
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = ((User) req.getSession().getAttribute("user")).getId();
        User user = ValidateService.getInstance().findById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
        }
}
