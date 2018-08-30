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
public class UserCreateServlet extends HttpServlet {
    private final ActionDispatch dispatch = ActionDispatch.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>"
                + "<html lang = \"en\">"
                + "<head>"
                + "<meta charset = \"UTF-8\">"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/create' method='post'>"
                + "<input type='hidden' name='action' value='add'>"
                + "Name : <input type='text' name='name'>"
                + "</br>"
                + "Email : <input type='text' name='email'>"
                + "</br>"
                + "Login : <input type='text' name='login'>"
                + "</br>"
                + "<input type='submit' value='Create'>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.dispatch.init();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(this.dispatch.execute(req));
        writer.flush();
    }
}
