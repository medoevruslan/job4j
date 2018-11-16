package ru.job4j.todolist.controller;

import ru.job4j.todolist.persistent.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener closes the SessionFactory.
 */
public class Init implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.closeFactory();
    }
}
