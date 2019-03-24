package ru.job4j.carstore.controller;

import ru.job4j.carstore.StaticMapper;
import ru.job4j.carstore.entity.Item;
import ru.job4j.carstore.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ItemFilterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        List<Item> items = ItemService.getInstance().filter(req);
        String result = StaticMapper.getInstance().unproxyAndJson(items);
        writer.write(result);
        writer.flush();
    }
}
