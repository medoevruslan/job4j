package ru.job4j.meloman.dao.implementation;

import org.apache.log4j.Logger;
import ru.job4j.meloman.dao.DAO;
import ru.job4j.meloman.dao.FactoryDao;
import ru.job4j.meloman.dao.RoleRepository;
import ru.job4j.meloman.entity.Role;
import ru.job4j.meloman.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Role DAO implementation.
 */
public class RoleManager implements DAO<Role>, RoleRepository {
    private final static Logger LOG = Logger.getLogger(RoleManager.class);

    /**
     * Add a role to a database.
     * @param model Role to add.
     * @return true.
     */
    @Override
    public boolean add(Role model) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.ADD.query)) {
            statement.setString(1, model.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't add role", e);
        }
        return true;
    }

    /**
     * Delete a role from a database.
     * @param model Role to delete.
     * @return true.
     */
    @Override
    public boolean remove(Role model) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.DELETE.query)) {
            statement.setInt(1, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't delete role", e);
        }
        return false;
    }

    /**
     * Update a role in a database.
     * @param data Data to update the role.
     * @return true.
     */
    @Override
    public boolean update(Role data) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.UPDATE.query))  {
            statement.setString(1, data.getName());
            statement.setInt(2, data.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't update role", e);
        }
        return true;
    }

    /**
     * Get a role by id.
     * @param id Id to find a role.
     * @return found role.
     */
    @Override
    public Role findById(int id) {
        Role role = Role.EMPTY;
        ResultSet rset = null;
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.GET_BY_ID.query)) {
            statement.setInt(1, id);
            rset = statement.executeQuery();
            while (rset.next()) {
                role = new Role(id, rset.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error("Can't find role", e);
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    LOG.error("Can't close ResSet", e);
                }
            }
        }
        return role;
    }

    /**
     * Get a list of all roles.
     * @return role's list.
     */
    @Override
    public List<Role> findAll() {
        List<Role> result = new ArrayList<>();
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rset = statement.executeQuery(SQLQuery.GET_ALL.query)) {
            while (rset.next()) {
                result.add(new Role(rset.getInt("id"), rset.getString("name")));
            }
        } catch (SQLException e) {
            LOG.error("Can't get all roles", e);
        }
        return result;
    }

    /**
     * Receive a list of all users by role.
     * @param role Role to get a list of users.
     * @return list of users.
     */
    @Override
    public List<User> getUserByRole(Role role) {
        List<User> result = new ArrayList<>();
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rset = statement.executeQuery(SQLQuery.GET_USERS.query)) {
            while (rset.next()) {
                User user = new User(rset.getInt("id"), rset.getString("name"));
                user.setLogin(rset.getString("login"));
                user.setPassword(rset.getString("password"));
                user.setRole(role);
                result.add(user);
            }
        } catch (SQLException e) {
            LOG.error("Can't get users", e);
        }
        return result;
    }

    /**
     * Enum of queries.
     */
    enum SQLQuery {
        ADD("INSERT INTO role (name) VALUES (?)"),
        UPDATE("UPDATE role SET name = ? WHERE id = ?"),
        GET_BY_ID("SELECT * FROM role WHERE id = ?"),
        GET_ALL("SELECT * FROM role"),
        DELETE("DELETE FROM role WHERE id = ?"),
        GET_USERS("SELECT * FROM users WHERE role_id = ?");

        private String query;

        SQLQuery(String query) {
            this.query = query;
        }
    }
}

