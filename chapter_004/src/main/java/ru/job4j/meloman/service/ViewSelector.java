package ru.job4j.meloman.service;

import ru.job4j.meloman.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class to define where to forward the request.
 */
public class ViewSelector {
    private static final ConcurrentHashMap<String, String> VIEWS = new ConcurrentHashMap<>();

    /*
     * Fill a map of views.
     */
    static {
        VIEWS.put("admin", "WEB-INF/views/AdminEditView.jsp");
        VIEWS.put("user", "WEB-INF/views/UserEditView.jsp");
        VIEWS.put("moderator", "WEB-INF/views/ModeratorEditView.jsp");
    }

    private static final ViewSelector INSTANCE = new ViewSelector();
    private ViewSelector() { }

    public static ViewSelector selector() {
        return INSTANCE;
    }

    /**
     * Select view to forward request.
     * @param req HttpServletRequest to get a session.
     * @return JSP page.
     */
    public String selectView(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return VIEWS.get(user.getRole().getName());
    }
}
