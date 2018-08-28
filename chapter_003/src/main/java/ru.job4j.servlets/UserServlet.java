package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public class UserServlet extends HttpServlet {
    private final ValidateService validate = ValidateService.getInstance();
    private final ActionDispatch dispatch = ActionDispatch.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        for (User user : validate.findAll()) {
            writer.append(String.valueOf(user.getId())).append("\n")
                    .append(user.getName()).append("\n")
                    .append(user.getEmail()).append("\n")
                    .append(user.getLogin()).append("\n")
                    .append(String.valueOf(user.getCreateDate())).append("\n");
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        dispatch.init();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(this.dispatch.execute(req));
        writer.flush();
    }
}
