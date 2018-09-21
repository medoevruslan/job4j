package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.servlets.ajax.DynamicStore;
import ru.job4j.servlets.ajax.JsonUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * Controller for converting request data to Json object.
 */
public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String result;
        while ((result = reader.readLine()) != null) {
            builder.append(result);
        }
        result = builder.toString();
        ObjectMapper mapper = new ObjectMapper();
        JsonUser user = mapper.readValue(result, JsonUser.class);
        DynamicStore.getInstance().add(user);
        reader.close();
    }
}
