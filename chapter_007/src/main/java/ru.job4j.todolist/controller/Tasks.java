package ru.job4j.todolist.controller;

import ru.job4j.todolist.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet which fetch items from database.
 */
public class Tasks extends HttpServlet {
    private final ItemService service = ItemService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       service.findItems(resp, service::findAll);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.findItems(resp, service::findUndone);
    }
}
