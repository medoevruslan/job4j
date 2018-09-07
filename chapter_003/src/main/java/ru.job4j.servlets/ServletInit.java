package ru.job4j.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * Check and initialize SQL tables.
 * Transfer user's roles as attribute to the context path.
 */
public class ServletInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        DBStore store = DBStore.getInstance();
        store.checkTable("tables.properties");
        List<String> roles = store.getRoles();
        sc.setAttribute("roles", roles);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
