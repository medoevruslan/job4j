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
 * Class for store elements in Postgresql
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
     * @return Role's list.
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
     * Get list of countries.
     * @return Countries list.
     */
    public List<String> getCountries() {
        List<String> rst = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from country");
            while (rs.next()) {
                rst.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error("Can't get countries", e);
        }
        return rst;
    }

    /**
     * Add new country to database.
     * @param country New country.
     */
    public void addCountry(String country) {
        try (Connection conn = SOURCE.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into country(name)values(?)");
            ps.setString(1, country);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't add new country", e);
        }
    }

    /**
     * Get list of all cities.
     * @return City's list.
     */
    public List<String> getAllCities() {
        List<String> rst = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from city");
            while (rs.next()) {
                rst.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error("Can't get cities", e);
        }
        return rst;
    }

    /**
     * Get list of city in country.
     * @param country Country name.
     * @return List of county's cities.
     */
    public List<String> getCitiesByCountry(String country) {
        List<String> rst = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from city where country_id = "
                    + "(select id from country where name = ?) ");
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rst.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rst;
    }

    /**
     * Add new city to database.
     * @param city New city.
     */
    public void addCity(String city) {
        try (Connection conn = SOURCE.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into city(name)values(?)");
            ps.setString(1, city);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't add new city", e);
        }
    }

    /**
     * Add new user to database.
     * @param user New user.
     * @return True if user added or false if it already exists.
     */
    @Override
    public boolean add(User user) {
        boolean rst = false;
        try (Connection conn = SOURCE.getConnection()) {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(
                        "insert into users(name, email, login, password, create_date, role_id, country_id, city_id) "
                                + "values (?, ?, ?, ?, ?, (select id from role where name = ?)"
                                + ", (select id from country where name = ?), (select id from city where name = ?))");
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getLogin());
                ps.setString(4, user.getPassword());
                ps.setTimestamp(5, Timestamp.valueOf(user.getCreateDate()));
                ps.setString(6, user.getRole());
                ps.setString(7, user.getCountry());
                ps.setString(8, user.getCity());
                ps.executeUpdate();
                rst = true;
            }
        } catch (SQLException e) {
            LOG.error("Can't add an object", e);
        }
        return rst;
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
    public boolean update(User user, String name, String email, String login,  String password, String country,
                          String city, String role) {
        try (Connection conn = SOURCE.getConnection()) {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(
                        "update users set name = ?, email = ?, login = ?, password = ?"
                                + ", country_id = (select id from country where name = ?)"
                                + ", city_id = (select id from city where name = ?) "
                                + ", role_id = (select id from role where name = ?) where id = ?");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, login);
                ps.setString(4, password);
                ps.setString(5, country);
                ps.setString(6, city);
                ps.setString(7, role);
                ps.setInt(8, user.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error("Can't update user", e);
        }
        return true;
    }

    /**
     * Delete user from database.
     * @param user User for delete.
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
                        "select u.id, u.name as u_name, email, login, password, r.name as r_name, co.name as co_name"
                                + ", ci.name as ci_name from users as u, role as r, city as ci, country as co "
                                + "where r.id = u.role_id and co.id = u.country_id and ci.id = u.city_id and u.id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("u_name");
                    String email = rs.getString("email");
                    String login = rs.getString("login");
                    String password = rs.getString("password");
                    String country = rs.getString("co_name");
                    String city = rs.getString("ci_name");
                    String role = rs.getString("r_name");
                    user = new User(name, email, login, password);
                    user.setId(id);
                    user.setCity(new City(city));
                    user.setCountry(new Country(country));
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
                        "select u.id, u.name as u_name, email, login, password, r.name as r_name, co.name as co_name"
                                + ", ci.name as ci_name from users as u, role as r, city as ci, country as co "
                                + "where r.id = u.role_id and co.id = u.country_id and ci.id = u.city_id");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("u_name");
                    String email = rs.getString("email");
                    String login = rs.getString("login");
                    String password = rs.getString("password");
                    String country = rs.getString("co_name");
                    String city = rs.getString("ci_name");
                    String role = rs.getString("r_name");
                    User user = new User(name, email, login, password);
                    user.setId(id);
                    user.setCity(new City(city));
                    user.setCountry(new Country(country));
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
