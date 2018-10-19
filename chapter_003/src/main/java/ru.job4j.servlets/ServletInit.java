package ru.job4j.servlets;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

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
 * Starts liquibase to init test tables.
 * Transfer user's roles as attribute to the context path.
 */
public class ServletInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        DBStore store = DBStore.getInstance();
//        store.checkTable("tables.properties");
        JdbcConnection jdbcConnection = new JdbcConnection(DBStore.connection());
        try {
            Liquibase liquibase = new Liquibase(
                    "liquibase/db-changelog-master.xml", new ClassLoaderResourceAccessor(), jdbcConnection);
            liquibase.update("");
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
        List<String> roles = store.getRoles();
        List<String> countries = store.getCountries();
        sc.setAttribute("roles", roles);
        sc.setAttribute("countries", countries);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
