package ru.job4j.servlets;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

 /*
 * Class for store elements in Postrgresql
 */
public class DBStore implements Store<User> {
    private static final Logger LOG = Logger.getLogger(DBStore.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    private DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/postgres");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    /**
     * Method checks if given table exists.
     * @param table Table to check.
     */
    protected void checkTable(String table) {
        try (Connection conn = SOURCE.getConnection();
             InputStream is = this.getClass().getClassLoader().getResourceAsStream(table)) {
            Properties props = new Properties();
            props.load(is);
            for (String tbl : props.stringPropertyNames()) {
                String sql = props.getProperty(tbl);
                DatabaseMetaData data = conn.getMetaData();
                ResultSet rst = data.getTables(
                        null, null, tbl, new String[]{"TABLE"});
                if (!rst.next()) {
                    Statement st = conn.createStatement();
                    st.execute(sql);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Get list of roles from database.
     * @return
     */
    public List<String> getRoles() {
        List<String> list = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection()) {
            if (conn != null) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from role");
                while (rs.next()) {
                    list.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            LOG.error("Can't get roles", e);
        }
        return list;
    }

    /**
     * Add new roles to database.
     * @param role New role.
     */
    public void addRole(String role) {
        try (Connection conn = SOURCE.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into role(name)values(?)");
            ps.setString(1, role);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't add new role", e);
        }
    }

    /**
     * Add new user to database.
     * @param user New user.
     * @return true.
     */
    @Override
    public boolean add(User user) {
        try (Connection conn = SOURCE.getConnection()) {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(
                        "insert into users(name, email, login, password, create_date, role_id) "
                                + "values (?, ?, ?, ?, ?, (select id from role where name = ?))");
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getLogin());
                ps.setString(4, user.getPassword());
                ps.setTimestamp(5, Timestamp.valueOf(user.getCreateDate()));
                ps.setString(6, user.getRole());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error("Can't add an object", e);
        }
        return true;
    }

    /**
     * Edit user parametres.
     * @param user User for edit.
     * @param name User's name.
     * @param email User's email.
     * @param login User's login.
     * @param password User's password.
     * @param role User's role.
     * @return true.
     */
    @Override
    public boolean update(User user, String name, String email, String login,  String password, String role) {
        try (Connection conn = SOURCE.getConnection()) {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(
                        "update users set name = ?, email = ?, login = ?, password = ?, role_id = ("
                                + "select id from role where name = ?) where id = ?");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, login);
                ps.setString(4, password);
                ps.setString(5, role);
                ps.setInt(6, user.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error("Can't update user", e);
        }
        return true;
    }

    /**
     * Delete user from database.
     * @param user Uset for delete.
     * @return true.
     */
    @Override
    public boolean delete(User user) {
        try  (Connection conn = SOURCE.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("delete from users where id = ?");
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't delete user", e);
        }
        return true;
    }

    /**
     * Find by user's id.
     * @param id User ID.
     * @return true.
     */
    @Override
    public User findById(int id) {
        User user = null;
        try (Connection conn = SOURCE.getConnection()) {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(
                        "select u.id, u.name as u_name, email, login, password, r.name as r_name "
                                + "from users as u inner join role as r on(u.role_id = r.id) where u.id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("u_name");
                    String email = rs.getString("email");
                    String login = rs.getString("login");
                    String password = rs.getString("password");
                    String role = rs.getString("r_name");
                    user = new User(name, email, login, password);
                    user.setId(id);
                    user.setRole(new Role(role));
                }
            }
        } catch (SQLException e) {
            LOG.error("Can't find user by id", e);
        }

        return user;
    }

    /**
     * Get list of all users from database.
     * @return User's list.
     */
    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection()) {
            if (conn != null) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(
                        "select u.id, u.name as u_name, email, login, password, r.name as r_name "
                                + "from users as u inner join role as r on(u.role_id = r.id)");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("u_name");
                    String email = rs.getString("email");
                    String login = rs.getString("login");
                    String password = rs.getString("password");
                    String role = rs.getString("r_name");
                    User user = new User(name, email, login, password);
                    user.setId(id);
                    user.setRole(new Role(role));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            LOG.error("Can't get user list", e);
        }
        return users;
    }
}
