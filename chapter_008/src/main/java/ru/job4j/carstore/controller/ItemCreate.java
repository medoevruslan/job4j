package ru.job4j.carstore.controller;

import ru.job4j.carstore.ItemDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller manages process of new item creating.
 */
public class ItemCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/NewItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ItemDispatcher.getInstance().createItem(req);
        resp.sendRedirect(String.format("%s/secure/profile", req.getContextPath()));
    }
}
