package userauthcache.servlet;

import userauthcache.entity.User;
import userauthcache.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Stores new user and checks if his login is unique.
 */
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        boolean exist = false;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        for(User user : Store.getInstance().findAll()) {
            if (user.getLogin().equals(login)) {
                exist = true;
                writer.write(String.valueOf(exist));
                writer.flush();
                break;
            }
        }
        if (!exist) {
            User user = new User(login, password);
            req.getSession().setAttribute("user", Store.getInstance().add(user));
            writer.write(String.valueOf(exist));
            writer.flush();
        }
    }
}
