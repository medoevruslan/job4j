package ru.job4j.carstore.controller;

import ru.job4j.carstore.entity.Manufacturer;
import ru.job4j.carstore.entity.Model;
import ru.job4j.carstore.service.BodyService;
import ru.job4j.carstore.service.ManufacturerService;
import ru.job4j.carstore.service.TransmissionService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Controller passes all data via session that are needed to create new item.
 */
public class CreateItemLoader implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getMethod().equalsIgnoreCase("get")) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            HttpSession session = req.getSession();
            if (session.getAttribute("manufacturers") != null) {
                req.getRequestDispatcher("/WEB-INF/views/NewItem.jsp").forward(req, resp);
                return;
            } else {
                List<Manufacturer> manufacturers = ManufacturerService.getInstance().findAll();
                ConcurrentHashMap<String, List<Model>> manufAndModels = new ConcurrentHashMap<>();
                for (Manufacturer manf : manufacturers) {
                    manufAndModels.put(manf.getName(), manf.getModels());
                }
                session.setAttribute("manufacturers", manufacturers);
                session.setAttribute("manufAndModels", manufAndModels);
                session.setAttribute("transmissions", TransmissionService.getInstance().findAll());
                session.setAttribute("bodies", BodyService.getInstance().findAll());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void destroy() { }
}
