package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * User's edit page.
 */
public class UserUpdateServlet extends HttpServlet {
    private final ActionDispatch dispatch = ActionDispatch.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        req.setAttribute("user", ValidateService.getInstance().findById(id));
        req.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp").forward(req, resp);
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
