package userauthcache.servlet;

import userauthcache.entity.User;
import userauthcache.store.Store;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Filter checks if user is already authorized.
 * If not than it redirects him to login page.
 */

public class CheckAuth implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        Cookie[] cookies = req.getCookies();
        Cookie userCookie = null;
        if (cookies != null) {
            userCookie = Stream.of(cookies).filter(cookie -> cookie.getName()
                    .equals("userId")).findAny().orElse(null);
        }
        if (!loggedIn) {
            if (userCookie != null) {
                User user = Store.getInstance().findById(Integer.parseInt(userCookie.getValue()));
                req.getSession().setAttribute("user", user);
            }
        }
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        } else {
            chain.doFilter(request, response);
        }
    }
}
