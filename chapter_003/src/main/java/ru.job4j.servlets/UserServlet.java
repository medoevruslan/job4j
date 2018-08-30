package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm");
        StringBuilder builder = new StringBuilder("<table border='1 px solid black' cellspacing='0' cellpadding='7'>");
        for (User user : this.validate.findAll()) {
            builder.append("<tr><td>").append(user.getId()).append("</td>")
                    .append("<td>").append(user.getName()).append("</td>")
                    .append("<td>").append(user.getEmail()).append("</td>")
                    .append("<td>").append(user.getLogin()).append("</td>")
                    .append("<td>").append(user.getCreateDate().format(formatter)).append("</td>")
                    .append("<form action='" + req.getContextPath() + "/edit' method='get'>")
                    .append("<input type='hidden' name='id' value='" + user.getId() + "'>")
                    .append("<td><input type='submit' value='Edit'></form></td>")
                    .append("<form action='" + req.getContextPath() + "/edit' method='post'>")
                    .append("<input type='hidden' name='action' value='delete'>")
                    .append("<input type='hidden' name='id' value='" + user.getId() + "'>")
                    .append("<td><input type='submit' value='Delete'></form></td></tr>");
        }
        builder.append("</table>");

        writer.append("<!DOCTYPE html>"
                + "<html lang = \"en\">"
                + "<head>"
                + "<meta charset = \"UTF-8\">"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/create' method='get'>"
                + "Create user :"
                + "<input type='submit' value='Push'>"
                + "</form>"
                + "</br>"
                + builder.toString()
                + "</body>"
                + "</html>"
        );
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
