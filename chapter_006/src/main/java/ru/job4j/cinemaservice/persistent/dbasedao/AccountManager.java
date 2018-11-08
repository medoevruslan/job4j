package ru.job4j.cinemaservice.persistent.dbasedao;

import org.apache.log4j.Logger;
import ru.job4j.cinemaservice.utils.AutoRollback;
import ru.job4j.cinemaservice.model.Account;
import ru.job4j.cinemaservice.persistent.AccountDAO;
import ru.job4j.cinemaservice.persistent.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Account DAO.
 */
public class AccountManager implements AccountDAO {
    private static final Logger LOG = Logger.getLogger(AccountManager.class);

    /**
     * Method adds model to database.
     * @param model Model to add.
     * @return Id of added model.
     */
    @Override
    public int add(Account model) {
        int id = -1;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.ADD.query)) {
            statement.setString(1, model.getLastName());
            statement.setString(2, model.getMiddleName());
            statement.setString(3, model.getFirstName());
            statement.setInt(4, model.getPhoneNumber());
            ResultSet rset = statement.executeQuery();
            while (rset.next()) {
                id = rset.getInt("id");
            }
            rollback.commit();
            this.closeRSet(rset);
        } catch (SQLException e) {
            LOG.error("Can't add account", e);
        }
        return id;
    }

    /**
     * Updates the model.
     * @param model Model to update.
     * @return Result of update (true or false).
     */
    @Override
    public boolean update(Account model) {
        boolean rst = false;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.UPDATE.query)) {
            statement.setString(1, model.getLastName());
            statement.setString(2, model.getMiddleName());
            statement.setString(3, model.getFirstName());
            statement.setInt(4, model.getPhoneNumber());
            statement.setInt(5, model.getId());
            rst = statement.executeUpdate() > 0;
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't update account", e);
        }
        return rst;
    }

    /**
     * Removes the model from database.
     * @param model Model to remove.
     * @return Result of remove (true or false).
     */
    @Override
    public boolean remove(Account model) {
        boolean result = false;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.DELETE.query)) {
            statement.setInt(1, model.getId());
            result = statement.executeUpdate() > 0;
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't delete account", e);
        }
        return result;
    }

    /**
     * Finds the model by id.
     * @param id Id to find the model.
     * @return Model.
     */
    @Override
    public Optional<Account> findById(int id) {
        Optional<Account> result = Optional.empty();
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.FIND_BY_ID.query)) {
            statement.setInt(1, id);
            ResultSet rset = statement.executeQuery();
            while (rset.next()) {
                result = Optional.of(this.constructAccount(rset));
            }
            rollback.commit();
            this.closeRSet(rset);
        } catch (SQLException e) {
            LOG.error("Can't find account by id", e);
        }
        return result;
    }

    /**
     * Finds all exists models.
     * @return List of models.
     */
    @Override
    public List<Account> findAll() {
        List<Account> result = Collections.EMPTY_LIST;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             Statement statement = conn.createStatement();
             ResultSet rset = statement.executeQuery(Query.FIND_ALL.query)) {
            while (rset.next()) {
                result = new ArrayList<>();
                result.add(this.constructAccount(rset));
            }
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't findAll accounts", e);
        }
        return result;
    }

    /**
     * Helps construct the model by given ResultSet.
     * @param rset ResultSet to build the model.
     * @return Model.
     * @throws SQLException SQLException.
     */
    private Account constructAccount(ResultSet rset) throws SQLException {
        int id = rset.getInt("id");
        String lastName = rset.getString("last_name");
        String middleName = rset.getString("middle_name");
        String firstName = rset.getString("first_name");
        String name = String.format("%s %s %s", lastName, middleName, firstName);
        Account account = new Account(id, name);
        account.setPhoneNumber(rset.getInt("phone"));
        return account;
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

        ADD("INSERT INTO accounts(last_name, middle_name, first_name, phone) "
                + "VALUES(?, ?, ?, ?) RETURNING id"),
        UPDATE("UPDATE accounts SET last_name = ?, middle_name = ?, first_name = ?, phone = ? WHERE id = ?"),
        DELETE("DELETE FROM accounts WHERE id = ?"),
        FIND_BY_ID("SELECT * FROM accounts WHERE id = ?"),
        FIND_ALL("SELECT * FROM accounts");

        private String query;

        Query(String query) {
            this.query = query;
        }
    }
}
