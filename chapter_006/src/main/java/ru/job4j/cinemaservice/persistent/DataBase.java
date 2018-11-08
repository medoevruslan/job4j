package ru.job4j.cinemaservice.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DataBase provides us with connection and initializes connection's pool.
 */
public class DataBase {
    private final static Logger LOG = Logger.getLogger(DataBase.class);
    private final BasicDataSource source = new BasicDataSource();
    private static final DataBase INSTANCE = new DataBase();

    private DataBase() { }

    public static DataBase getInstance() {
        return INSTANCE;
    }

    public Connection getConnetion() throws SQLException {
        return this.source.getConnection();
    }

    public void setConfig() {
        this.source.setDriverClassName("org.postgresql.Driver");
        this.source.setUrl("jdbc:postgresql://localhost:5432/postgres");
        this.source.setUsername("postgres");
        this.source.setPassword("");
        this.source.setMaxOpenPreparedStatements(100);
        this.source.setMaxIdle(10);
        this.source.setMinIdle(5);
    }

    public void setConfig(String config) {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(config)) {
            Properties prop = new Properties();
            prop.load(is);
            this.source.setDriverClassName(prop.getProperty("jdbc.driver"));
            this.source.setUrl(prop.getProperty("jdbc.url"));
            this.source.setUsername(prop.getProperty("jdbc.user"));
            this.source.setPassword(prop.getProperty("jdbc.password"));
            this.source.setMaxOpenPreparedStatements(100);
            this.source.setMaxIdle(10);
            this.source.setMinIdle(5);
        } catch (IOException e) {
            LOG.error("Can't set config for DataBase");
        }
    }

    public void closeDBase() {
        try {
            this.source.close();
        } catch (SQLException e) {
            LOG.error("Can't close connection pool", e);
        }
    }
}
