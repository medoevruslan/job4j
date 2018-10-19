package ru.job4j.meloman.dao.implementation;

import org.apache.log4j.Logger;
import ru.job4j.meloman.dao.DAO;
import ru.job4j.meloman.dao.FactoryDao;
import ru.job4j.meloman.entity.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Address DAO implementation.
 */
public class AddressManager implements DAO<Address> {
    private final static Logger LOG = Logger.getLogger(AddressManager.class);

    /**
     * Add an address to a database.
     * @param model An address to add.
     * @return true.
     */
    @Override
    public boolean add(Address model) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.ADD.query)) {
            statement.setString(1, model.getCity());
            statement.setString(2, model.getStreet());
            statement.setInt(3, model.getHouse());
            statement.setInt(4, model.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't add address", e);
        }
        return true;
    }

    /**
     * Remove an address from database.
     * @param model An address to remove.
     * @return true.
     */
    @Override
    public boolean remove(Address model) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.DELETE.query)) {
            statement.setInt(1, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't delete address", e);
        }
        return true;
    }

    /**
     * Update an address in a database.
     * @param data Data to update an address.
     * @return true.
     */
    @Override
    public boolean update(Address data) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.UPDATE.query)) {
            statement.setString(1, data.getCity());
            statement.setString(2, data.getStreet());
            statement.setInt(3, data.getHouse());
            statement.setInt(4, data.getUserId());
            statement.setInt(5, data.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't update address", e);
        }
        return true;
    }

    /**
     * Receive an address by its id.
     * @param id An address's id.
     * @return found address.
     */
    @Override
    public Address findById(int id) {
        Address address = Address.EMPTY;
        ResultSet rset = null;
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.GET_BY_ID.query)) {
            statement.setInt(1, id);
            rset = statement.executeQuery();
            while (rset.next()) {
                String city = rset.getString("city");
                String street = rset.getString("street");
                int house = rset.getInt("house");
                String fullAdr = String.format("%s, %s, %d", city, street, house);
                address = new Address(rset.getInt("id"), fullAdr);
                address.setCity(city);
                address.setHouse(house);
                address.setStreet(street);
            }
        } catch (SQLException e) {
            LOG.error("Can't find address", e);
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    LOG.error("Can't close ResSet", e);
                }
            }
        }
        return address;
    }

    /**
     * Receive a list of all addresses from a database.
     * @return list of addresses.
     */
    @Override
    public List<Address> findAll() {
        List<Address> result = new ArrayList<>();
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rset = statement.executeQuery(SQLQuery.GET_ALL.query)) {
            while (rset.next()) {
                String city = rset.getString("city");
                String street = rset.getString("street");
                int house = rset.getInt("house");
                String fullAdr = String.format("%s, %s, %d", city, street, house);
                Address address = new Address(rset.getInt("id"), fullAdr);
                address.setCity(city);
                address.setStreet(street);
                address.setHouse(house);
                result.add(address);
            }
        } catch (SQLException e) {
            LOG.error("Can't get all addresses", e);
        }
        return result;
    }

    /**
     * Enum of queries.
     */
    enum SQLQuery {
        ADD("INSERT INTO address (city, street, house, user_id) VALUES (?, ?, ?, ?)"),
        UPDATE("UPDATE address SET city = ?, street = ?, house = ?, user_id = ? WHERE id = ?"),
        GET_BY_ID("SELECT * FROM address WHERE id = ?"),
        GET_ALL("SELECT * FROM address"),
        DELETE("DELETE FROM address WHERE id = ?");

        private String query;

        SQLQuery(String query) {
            this.query = query;
        }
    }
}
