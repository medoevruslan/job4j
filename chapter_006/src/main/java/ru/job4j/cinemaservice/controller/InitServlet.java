package ru.job4j.cinemaservice.controller;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import ru.job4j.cinemaservice.persistent.DataBase;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.sql.SQLException;

/**
 * Servlet that initializes configuration for PostgreSQL database, Log4j and starts Liquibase.
 */
public class InitServlet implements ServletContextListener {
    private final DataBase dataBase = DataBase.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String log4jPath = sc.getInitParameter("log4jConfiguration");
        String webapp = sc.getRealPath("/");
        String log4jFullPath = webapp + log4jPath;
        System.setProperty("file.name", String.format("/%s/cinema.log", webapp));
        File file = new File(log4jFullPath);
        if (file.exists()) {
            PropertyConfigurator.configure(log4jFullPath);
        } else {
            BasicConfigurator.configure();
        }
        dataBase.setConfig();
        try {
            JdbcConnection connection = new JdbcConnection(dataBase.getConnetion());
            Liquibase liquibase = new Liquibase(
                    "liquibase/db-changelog-master.xml", new ClassLoaderResourceAccessor(), connection);
            liquibase.update("");
        } catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       dataBase.closeDBase();
    }
}
