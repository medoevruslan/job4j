package ru.job4j.meloman.controller;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import ru.job4j.meloman.dao.FactoryDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.sql.SQLException;

/**
 * Servlet that initializes configuration for PostgreSQL database, Log4j and starts Liquibase.
 */

public class InitServlet implements ServletContextListener {
    private final FactoryDao factory = FactoryDao.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String log4jConfPath = sc.getInitParameter("log4jConfiguration");
        String webApp = sc.getRealPath("/");
        String log4j = webApp + log4jConfPath;
        System.setProperty("file.name", String.format("/%s/meloman.log", webApp));
        File file = new File(log4j);
        if (file.exists()) {
            PropertyConfigurator.configure(log4j);
        } else {
            BasicConfigurator.configure();
        }
        factory.setDbConfig();
        try {
            JdbcConnection connection = new JdbcConnection(FactoryDao.SOURCE.getConnection());
            Liquibase liquibase = new Liquibase(
                    "liquibase/db-changelog-master.xml", new ClassLoaderResourceAccessor(), connection);
            liquibase.update("");
        } catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
        }
        sc.setAttribute("roles", factory.getRoleDao().findAll());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        factory.closeSOURCE();
    }
}

