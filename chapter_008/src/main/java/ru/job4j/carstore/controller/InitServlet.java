package ru.job4j.carstore.controller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * Servlet sets up log4j.
 */
public class InitServlet implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String log4j = sc.getInitParameter("log4j");
        String web = sc.getRealPath("/");
        String log4jPath = web + log4j;
        System.setProperty("log.fileName", String.format("/%s/carStore.log", web));
        File file = new File(log4jPath);
        if (file.exists()) {
            PropertyConfigurator.configure(log4jPath);
        } else {
            BasicConfigurator.configure();
        }
    }
}
