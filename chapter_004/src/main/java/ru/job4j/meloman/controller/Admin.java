package ru.job4j.meloman.controller;

import ru.job4j.meloman.dao.FactoryDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller that forwards to admin's page.
 */

public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", FactoryDao.getInstance().getUserDao().getAllEntities());
        req.getRequestDispatcher("WEB-INF/views/AdminView.jsp").forward(req, resp);
        }
}
