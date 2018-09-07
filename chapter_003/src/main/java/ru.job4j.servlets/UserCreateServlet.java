package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * New user's create page.
 */
public class UserCreateServlet extends HttpServlet {
    private final ActionDispatch dispatch = ActionDispatch.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/CreateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user;
        this.dispatch.initialize();
        this.dispatch.execute(req);
        HttpSession session = req.getSession();
        synchronized (session) {
            user = (User) session.getAttribute("user");
        }
        resp.sendRedirect(String.format("%s/%s", req.getContextPath(), user.getRole()));
    }

}
