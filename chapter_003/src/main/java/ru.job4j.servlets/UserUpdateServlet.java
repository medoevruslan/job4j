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
public class UserUpdateServlet extends HttpServlet {
    private final ActionDispatch dispatch = ActionDispatch.getInstance();
    private final ValidateService validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        int id = Integer.valueOf(req.getParameter("id"));
        User user = validate.findById(id);


        writer.append("<!DOCTYPE html>"
                + "<html lang = \"en\">"
                + "<head>"
                + "<meta charset = \"UTF-8\">"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/edit' method='post' "
                + "<input type='hidden' name='id' value='" + user.getId() + "'>"
                + "Id : <input type='text' name='id' value='" + user.getId() + "'>"
                + "Name : <input type='text' name='name' value='" + user.getName() + "'>"
                + "Email : <input type='text' name='email' value='" + user.getEmail() + "'>"
                + "Login : <input type='text' name='login' value='" + user.getLogin() + "'>"
                + "<input type='hidden' name='action' value='update'>"
                + "<input type='submit' value='Save'>"
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
