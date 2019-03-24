package ru.job4j.carstore.controller;

import ru.job4j.carstore.service.ItemService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller updates data of item.
 */
public class ItemUpdate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        boolean isSold = Boolean.valueOf(req.getParameter("isSold"));
        int id = Integer.parseInt(req.getParameter("id"));
        ItemService.getInstance().setSold(id, isSold);
    }
}
