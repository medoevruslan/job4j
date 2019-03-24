package ru.job4j.carstore.controller;

import ru.job4j.carstore.StaticMapper;
import ru.job4j.carstore.entity.*;
import ru.job4j.carstore.service.ItemService;
import ru.job4j.carstore.service.ManufacturerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller loads index page and pass data to fill in table with items.
 */
public class MainPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Item> items = ItemService.getInstance().findAll();
        req.setAttribute("items", StaticMapper.getInstance().unproxyAndJson(items));
        List<Manufacturer> manufacturers = ManufacturerService.getInstance().findAll();
        Map<String, List<Model>> manufAndModels = new HashMap<>();
        for (Manufacturer manf : manufacturers) {
            manufAndModels.put(manf.getName(), manf.getModels());
        }
        session.setAttribute("manufacturers", manufacturers);
        session.setAttribute("manufAndModels", manufAndModels);
        req.getRequestDispatcher("/WEB-INF/views/Index.jsp").forward(req, resp);
    }
}
