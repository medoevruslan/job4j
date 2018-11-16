package ru.job4j.todolist.controller;

import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet which creates new Item and adds it to database.
 */
public class Create extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String descr = req.getParameter("description");
        Item item = new Item();
        item.setDesc(descr);
        ItemService.getInstance().add(item);
    }
}
