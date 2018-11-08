package ru.job4j.cinemaservice.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class simplifies work with rollback of connection.
 */
public class AutoRollback implements AutoCloseable {

    private Connection connection;
    private boolean isComittet;

    public AutoRollback(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        this.connection.commit();
        this.isComittet = true;
    }

    @Override
    public void close() throws SQLException {
        if (!this.isComittet) {
            this.connection.rollback();
        }
    }
}
