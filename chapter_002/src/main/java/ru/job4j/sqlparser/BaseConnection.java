package ru.job4j.sqlparser;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class BaseConnection {
    private Logger log = Logger.getLogger(BaseConnection.class);
    private String config;

    public BaseConnection(String config) {
        this.config = config;
    }

    /**
     * Create database connection.
     * @param config Properties for connection.
     * @return Connection object.
     * @throws SQLException
     */
    private Connection connect(String config) throws SQLException {
        log.info("Create connection");
        Connection conn = null;
        try (InputStream is = new FileInputStream(config)) {
            Properties props = new Properties();
            props.load(is);
            String url = props.getProperty("jdbc.url");
            String user = props.getProperty("jdbc.user");
            String password = props.getProperty("jdbc.password");
            String driver = props.getProperty("jdbc.driver");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException ioe) {
            log.error("File not found", ioe);
        } catch (ClassNotFoundException e) {
            log.error("Driver not found", e);
        }
        return conn;
    }

    /**
     * Get last date of added vacancy .
     * @return Date of vacancy.
     */
    public Timestamp lastDate() {
        Timestamp date = null;
        try (Connection conn = this.connect(config)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet res = stmt.executeQuery("select create_date from vacancies order by create_date limit 1");
                date = res.getTimestamp("create_date");
            }
        } catch (SQLException e) {
            log.error("Can't get last vacansy's date ", e);
        }
        return date;
    }

    /**
     * Check if vacancy is unique and add to database.
     * @param list List of vacancies to add.
     */
    public void addToBase(List<Vacancy> list) {
        log.info("Add to base");
        if (!this.isTableExist()) {
            createTable();
        }
        try (Connection conn = this.connect(config)) {
            if (conn != null) {
                for (Vacancy vacancy : list) {
                    PreparedStatement stmt = conn.prepareStatement("select id from vacancies where name = ? ");
                    stmt.setString(1, vacancy.getName());
                    ResultSet rst = stmt.executeQuery();
                    if (!rst.next()) {
                        stmt = conn.prepareStatement("insert into vacancies(name, author, views, create_date) values(?, ?, ?, ?)");
                        stmt.setString(1, vacancy.getName());
                        stmt.setString(2, vacancy.getAutor());
                        stmt.setInt(3, vacancy.getViews());
                        stmt.setTimestamp(4, Timestamp.valueOf(vacancy.getVacansyDate()));
                        stmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Connection error", e);
        }
    }

    /**
     * Check if table exist.
     * @return True or false.
     * @throws SQLException
     */
    private boolean isTableExist() {
        boolean result = false;
        try (Connection conn = this.connect(config)) {
            DatabaseMetaData data = conn.getMetaData();
            ResultSet res = data.getTables(null, null, "vacancies", new String[]{"TABLE"});
            result = res.next();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Create table in database.
     * @throws SQLException
     */
    private void createTable() {
        log.info("Creating table");
        try (InputStream is = new FileInputStream(config);
             Connection conn = this.connect(config)) {
            if (conn != null) {
                Properties props = new Properties();
                props.load(is);
                String table = props.getProperty("table");
                Path path = Paths.get(table);
                Statement stmt = conn.createStatement();
                stmt.execute(new String(Files.readAllBytes(path), "UTF8"));
            }
        } catch (IOException e) {
            log.error("File not found - createTable method", e);
        } catch (SQLException sqle) {
            log.error("Connection eeror at createTable", sqle);
        }
    }

    /**
     * Obtain all vacancy's list.
     * @return Vacancy's list.
     */
    public List getVacancies() {
        log.error("Trying to get vacancies");
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection conn = this.connect(config)) {
            if (conn != null) {
                Statement stmnt = conn.createStatement();
                ResultSet rst = stmnt.executeQuery("select * from vacancies");
                while (rst.next()) {
                    String name = rst.getString("name");
                    String author = rst.getString("author");
                    int views = rst.getInt("views");
                    LocalDateTime date = rst.getTimestamp("create_date").toLocalDateTime();
                    vacancies.add(new Vacancy(name, author, views, date));
                }
            }
        } catch (SQLException e) {
            log.error("Connecion error", e);
        }
        return vacancies;
    }
}
