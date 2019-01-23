package userauthcache.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Invalidate session and clear cookie.
 */
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                cookie.setMaxAge(0);
                cookie.setValue(null);
                resp.addCookie(cookie);
                break;
            }
        }
        if (session != null) {
            session.invalidate();
        }
    }
}
