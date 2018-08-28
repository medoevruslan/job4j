package ru.job4j.servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public class ActionDispatch {
    private static ActionDispatch instance = new ActionDispatch();

    private ActionDispatch() { }

    public static ActionDispatch  getInstance() {
        return instance;
    }

    private final ConcurrentHashMap<String, Function<HttpServletRequest, String>> dispatcher = new ConcurrentHashMap<>();
    private final ValidateService validate = ValidateService.getInstance();

    private Function<HttpServletRequest, String> add() {
        return req -> {
            String name = req.getParameter("name");
            String login = req.getParameter("login");
            String email = req.getParameter("email");
            User user = new User(name, email, login);
            this.validate.add(user);
            return String.format("New user %s added", user);
        };
    }

    private Function<HttpServletRequest, String> delete() {
        return  req -> {
            int id = Integer.valueOf(req.getParameter("id"));
            User user = this.validate.findById(id);
            return this.validate.delete(user) ? String.format("User id %d deleted", user.getId()) : "Incorrect id";
        };
    }

    private Function<HttpServletRequest, String> update() {
        return req -> {
            int id = Integer.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            User user = this.validate.findById(id);
            return this.validate.update(user, name) ? String.format("User id %d updated", user.getId()) : "Incorrect id";
        };
    }

    private void load(String action, Function<HttpServletRequest, String> handler) {
        this.dispatcher.put(action, handler);
    }

    public void init() {
        this.load("add", this.add());
        this.load("delete", this.delete());
        this.load("update", this.update());
    }

    public String execute(HttpServletRequest req) {
        return this.dispatcher.get(req.getParameter("action")).apply(req);
    }
}
