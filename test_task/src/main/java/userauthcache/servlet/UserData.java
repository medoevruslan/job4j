package userauthcache.servlet;

import userauthcache.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * This servlet provides user's login to index page.
 */
public class UserData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        User user = (User) req.getSession().getAttribute("user");
        writer.write(user.getLogin());
        writer.flush();
    }
}
