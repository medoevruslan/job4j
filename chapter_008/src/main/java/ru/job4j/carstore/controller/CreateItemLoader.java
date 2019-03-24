package ru.job4j.carstore.controller;

import ru.job4j.carstore.service.BodyService;
import ru.job4j.carstore.service.TransmissionService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller passes all data via session that are needed to create new item.
 */
public class CreateItemLoader implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getMethod().equalsIgnoreCase("get")) {
            HttpSession session = req.getSession();
            session.setAttribute("transmissions", TransmissionService.getInstance().findAll());
            session.setAttribute("bodies", BodyService.getInstance().findAll());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void destroy() { }
}
