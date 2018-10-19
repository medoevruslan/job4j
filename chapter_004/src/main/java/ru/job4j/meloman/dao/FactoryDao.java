package ru.job4j.meloman.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import ru.job4j.meloman.dao.implementation.AddressManager;
import ru.job4j.meloman.dao.implementation.MusicManager;
import ru.job4j.meloman.dao.implementation.RoleManager;
import ru.job4j.meloman.dao.implementation.UserManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Class that makes a connection with a database and gives dao's instance.
 */

public class FactoryDao {
    private final static FactoryDao INSTANCE = new FactoryDao();
    public final static BasicDataSource SOURCE = new BasicDataSource();
    private static final Logger LOG = Logger.getLogger(FactoryDao.class);

    public static FactoryDao getInstance() {
        return INSTANCE;
    }

    private FactoryDao() { }

    /**
     * Set a default configuration of a database.
     */
    public void setDbConfig() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/postgres");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("");
        SOURCE.setMaxOpenPreparedStatements(100);
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
    }

    /**
     * Set a custom configuration of a database.
     */
    public void setDbConfig(String config) {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(config)) {
            Properties prop = new Properties();
            prop.load(is);
            SOURCE.setDriverClassName(prop.getProperty("jdbc.driver"));
            SOURCE.setUrl(prop.getProperty("jdbc.url"));
            SOURCE.setUsername(prop.getProperty("jdbc.user"));
            SOURCE.setPassword(prop.getProperty("jdbc.password"));
            SOURCE.setMaxOpenPreparedStatements(100);
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
        }
    }

    /**
     * Initialize database tables if not exists.
     * @param table Tables to initialize.
     */
    public void checkTable(String table) {
        try (Connection conn = SOURCE.getConnection();
             InputStream is = this.getClass().getClassLoader().getResourceAsStream(table);
             Statement statement = conn.createStatement()) {
            Properties props = new Properties();
            props.load(is);
            for (String sql : props.stringPropertyNames()) {
                String query = props.getProperty(sql);
                statement.execute(query);
            }
        } catch (SQLException | IOException e) {
            LOG.error("Can't checkTable", e);
        }
    }

    /**
     * Close database connection.
     */
    public void closeSOURCE() {
        try {
            SOURCE.close();
        } catch (SQLException e) {
            LOG.error("Can't close SOURCE", e);
        }
    }

    /**
     * Get user DAO.
     * @return UserDAO.
     */
    public UserManager getUserDao() {
        return new UserManager();
    }

    /**
     * Get role DAO.
     * @return RoleDAO.
     */
    public RoleManager getRoleDao() {
        return new RoleManager();
    }

    /**
     * Get music type DAO.
     * @return MusiTypeDAO.
     */
    public MusicManager getMusicDao() {
        return new MusicManager();
    }

    /**
     * Get address DAO.
     * @return AddressDAO.
     */
    public AddressManager getAddressDao() {
        return new AddressManager();
    }

}
