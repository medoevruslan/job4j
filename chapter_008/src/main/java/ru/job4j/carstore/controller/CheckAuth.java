package ru.job4j.carstore.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller that checks if user is logged in.
 */
public class CheckAuth extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            PrintWriter writer = resp.getWriter();
            writer.write("false");
            writer.flush();
        } else {
            req.getRequestDispatcher("/WEB-INF/views/NewItem.jsp").forward(req, resp);
        }
    }
}
