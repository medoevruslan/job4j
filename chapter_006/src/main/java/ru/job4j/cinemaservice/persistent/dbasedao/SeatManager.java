package ru.job4j.cinemaservice.persistent.dbasedao;

import org.apache.log4j.Logger;
import ru.job4j.cinemaservice.utils.AutoRollback;
import ru.job4j.cinemaservice.model.Account;
import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;
import ru.job4j.cinemaservice.persistent.DataBase;
import ru.job4j.cinemaservice.persistent.SeatDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Seat DAO.
 */
public class SeatManager implements SeatDAO {
    private static final Logger LOG = Logger.getLogger(SeatManager.class);

    /**
     * Method adds entity to database.
     * @param model Model to add.
     * @return Id of added entity.
     */
    @Override
    public int add(Seat model) {
        int result = -1;
        ResultSet rset = null;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.ADD.query)) {
            statement.setString(1, model.getName());
            statement.setInt(2, model.getRowNum());
            statement.setInt(3, model.getSeatNum());
            statement.setInt(4, model.getPrice());
            statement.setInt(5, model.getAccountId());
            rset = statement.executeQuery();
            while (rset.next()) {
                result = rset.getInt("id");
            }
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't add seat", e);
        } finally {
            this.closeRSet(rset);
        }
        return result;
    }

    /**
     * Method adds row to exists hall and sets price of seats.
     * @param seatsQnty Quantity of seats to add.
     * @param price Price of seats.
     * @param hall Hall to add the row.
     * @return True or False).
     */
    @Override
    public boolean addRow(int seatsQnty, int price, Hall hall) {
        boolean result = true;
        int hallId = hall.getId();
        int newRow = hall.getRows() + 1;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback autoRollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.ADD_ROW.query)) {
            for (int idx = 1; idx < seatsQnty + 1; idx++) {
                if (result) {
                    statement.setInt(1, newRow);
                    statement.setInt(2, idx);
                    statement.setInt(3, price);
                    statement.setInt(4, hallId);
                    result = statement.executeUpdate() > 1;
                } else {
                    break;
                }
            }
            autoRollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't add a row to hall", e);
        }
        return result;
    }

    /**
     * Updates the entity.
     * @param model Model to update.
     * @return Result of update (true or false).
     */
    @Override
    public boolean update(Seat model) {
        boolean result = false;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.UPDATE.query)) {
            statement.setInt(1, model.getPrice());
            statement.setInt(2, model.getAccountId());
            statement.setInt(3, model.getId());
            result = statement.executeUpdate() > 0;
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't update seat", e);
        }
        return result;
    }

    /**
     * Removes the entity from database.
     * @param model Model to remove.
     * @return Result of remove (true or false).
     */
    @Override
    public boolean remove(Seat model) {
        boolean result = false;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.DELETE.query)) {
            statement.setInt(1, model.getId());
            result = statement.executeUpdate() > 0;
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't delete seat", e);
        }
        return result;
    }

    /**
     * Finds Account by Seat.
     * @param seat Seat to find the Account.
     * @return Account.
     */
    @Override
    public Optional<Account> findAccountBySeat(Seat seat) {
        Optional<Account> result = Optional.empty();
        ResultSet rset = null;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.FIND_BY_SEAT.query)) {
            statement.setInt(1, seat.getId());
            rset = statement.executeQuery();
            while (rset.next()) {
                int id = rset.getInt("id");
                String lastName = rset.getString("last_name");
                String middleName = rset.getString("middle_name");
                String first = rset.getString("first_name");
                String name = String.format("%s %s %s", lastName, middleName, first);
                Account account = new Account(id, name);
                result = Optional.of(account);
                rollback.commit();
            }
        } catch (SQLException e) {
            LOG.error("Can't get account by seat", e);
        } finally {
            this.closeRSet(rset);
        }
        return result;
    }

    /**
     * Finds the entity by id.
     * @param id Id to find the entity.
     * @return Model.
     */
    @Override
    public Optional<Seat> findById(int id) {
        Optional<Seat> result = Optional.empty();
        ResultSet rset = null;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.FIND_BY_ID.query)) {
            statement.setInt(1, id);
            rset = statement.executeQuery();
            while (rset.next()) {
                int row = rset.getInt("row");
                int number = rset.getInt("number");
                String name = String.format("Row %s, Seat %s", row, number);
                Seat seat = new Seat(id, name);
                seat.setRowNum(row);
                seat.setSeatNum(number);
                seat.setPrice(rset.getInt("price"));
                seat.setAccountId(rset.getInt("account_id"));
                result = Optional.of(seat);
            }
            this.closeRSet(rset);
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't find seat by id", e);
        } finally {
            this.closeRSet(rset);
        }
        return result;
    }

    /**
     * Finds all exists models.
     * @return List of models.
     */
    @Override
    public List<Seat> findAll() {
        List<Seat> result = Collections.EMPTY_LIST;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             Statement statement = conn.createStatement();
             ResultSet rset = statement.executeQuery(Query.FIND_ALL.query)) {
            while (rset.next()) {
                result = new ArrayList<>();
                int row = rset.getInt("row");
                int number = rset.getInt("number");
                String name = String.format("Row %s, Seat %s", row, number);
                Seat seat = new Seat(rset.getInt("id"), name);
                seat.setRowNum(row);
                seat.setSeatNum(number);
                seat.setPrice(rset.getInt("price"));
                seat.setAccountId(rset.getInt("account_id"));
                result.add(seat);
            }
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't get list of seats", e);
        }
        return result;
    }

    /**
     * Closes ResultSet.
     * @param rset ResultSet to close.
     */
    private void closeRSet(ResultSet rset) {
        if (rset != null) {
            try {
                rset.close();
            } catch (SQLException e) {
                LOG.error("Can't close ResultSet", e);
            }
        }
    }

    /**
     * Enum of queries to work with Postgresql.
     */
    enum Query {

        ADD("INSERT INTO seats(name, row, number, price, account_id) VALUES(?, ?, ?, ?, ?) RETURNING id"),
        ADD_ROW("INSERT INTO seats(row, number, price, hall_id) VALUES(?, ?, ?, ?)"),
        UPDATE("UPDATE seats SET price = ?, account_id = ? WHERE id = ?"),
        DELETE("DELETE FROM seats WHERE id = ?"),
        FIND_BY_ID("SELECT * FROM seats WHERE id = ?"),
        FIND_BY_SEAT("SELECT acc.id FROM accounts AS acc, seats AS s WHERE s.account_id = acc.id AND s.id = ? "),
        FIND_ALL("SELECT * FROM seats");

        private String query;

        Query(String query) {
            this.query = query;
        }
    }
}
