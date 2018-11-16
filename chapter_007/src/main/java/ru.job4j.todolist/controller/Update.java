package ru.job4j.todolist.controller;

import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that updates condition of Item.
 */
public class Update extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        boolean isDone = Boolean.valueOf(req.getParameter("taskDone"));
        int id = Integer.parseInt(req.getParameter("taskId"));
        ItemService.getInstance().update(new Item(id, isDone));
    }
}
