package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * Controller provides us to fetch cities from database.
 */
public class CitiesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer =  resp.getWriter();
        resp.setContentType("json");
        String country = req.getParameter("country");
        List<String> countries = DBStore.getInstance().getCitiesByCountry(country);
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = mapper.writeValueAsString(countries);
        writer.write(jsonList);
        writer.flush();
    }
}
