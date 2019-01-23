package userauthcache.servlet;

import userauthcache.entity.User;
import userauthcache.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * This servlet checks if user's credentials are valid.
 * If valid than it stores his id into Cookie.
 */
public class LogIn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isValid = false;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean doRemember = Boolean.valueOf(req.getParameter("remember"));
        for(User user : Store.getInstance().findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                isValid = true;
                req.getSession().setAttribute("user", user);
                if (doRemember) {
                    Cookie cookie = new Cookie("userId", String.valueOf(user.getId()));
                    cookie.setMaxAge(24 * 60 * 60);
                    resp.addCookie(cookie);
                }
                break;
            }
        }
        if (!isValid) {
            Writer writer = resp.getWriter();
            writer.write(String.valueOf(isValid));
        }
    }
}
