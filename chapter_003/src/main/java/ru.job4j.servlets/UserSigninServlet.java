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
 * Checking if user's login and password are correct.
 */
public class UserSigninServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validate = ValidateService.getInstance();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (validate.isCorrect(login, password)) {
            User user = null;
            for (User usr : validate.findAll()) {
                if (login.equals(usr.getLogin())) {
                    user = usr;
                }
            }
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("user", user);
                session.setAttribute("login", user.getLogin());
            }
            resp.sendRedirect(String.format("%s/%s", req.getContextPath(), user.getRole()));
        } else {
            req.setAttribute("error", "Login or password is invalid");
            this.doGet(req, resp);
        }
    }
}
