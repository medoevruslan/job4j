package ru.job4j.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class StoreSQL {
    private final Logger logger = LoggerFactory.getLogger(StoreSQL.class);
    private File config;

    public StoreSQL(File config) {
        this.config = config;
    }

    public void generate(int n) {
        this.checkTable();
        PreparedStatement st = null;
        Connection connect = null;
        try {
            connect = this.connect();
            st = connect.prepareStatement("insert into table(entry) values(?) where id = ?");
            connect.setAutoCommit(false);
            for (int i = 0; i < n; i++) {
                st.setInt(1, i);
                st.setInt(2, i);
                st.executeUpdate();
            }
            connect.commit();
        } catch (SQLException sqle) {
            try {
                connect.rollback();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
            logger.error(sqle.getMessage(), sqle);
        } finally {
            try {
                st.close();
                connect.close();
            } catch (SQLException sqle) {
                logger.error(sqle.getMessage(), sqle);
            }
        }
    }

    private boolean isExist() {
        boolean rst = false;
        ResultSet res = null;
        Connection connect = null;
        try {
            connect = this.connect();
            DatabaseMetaData meta = connect.getMetaData();
            res = meta.getTables(null, null, "entry", new String[]{"TABLE"});
            rst = res.next();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                res.close();
                connect.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return rst;
    }

    private void checkTable() {
        Properties props = new Properties();
        Connection connect = null;
        InputStream is = null;
        Statement stmt = null;
        ResultSet rst = null;
        try {
            connect = this.connect();
            stmt = connect.createStatement();
            is = new FileInputStream(this.config);
            props.load(is);
            if (!this.isExist()) {
                String table = props.getProperty("table");
                Path path = Paths.get(table);
                stmt.executeUpdate(new String(Files.readAllBytes(path), "UTF-8"));
            }
            rst = stmt.executeQuery("select * from entry");
            if (rst.isBeforeFirst()) {
                stmt.executeQuery("delete from entry");
            }
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        } catch (SQLException sqle) {
            logger.error(sqle.getMessage(), sqle);
        } finally {
            try {
                rst.close();
                stmt.close();
                connect.close();
                is.close();
            } catch (SQLException sqle) {
                logger.error(sqle.getMessage(), sqle);
            } catch (IOException ioe) {
                logger.error(ioe.getMessage(), ioe);
            }
        }
    }

    public Connection connect() throws SQLException {
        Connection connect = null;
        Properties props = new Properties();
        try (InputStream is = new FileInputStream(this.config)) {
            props.load(is);
            String url = props.getProperty("url");
            String name = props.getProperty("name");
            String password = props.getProperty("password");
            connect =  DriverManager.getConnection(url, name, password);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return connect;
    }
}