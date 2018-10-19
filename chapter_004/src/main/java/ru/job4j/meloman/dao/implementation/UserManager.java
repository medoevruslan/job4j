package ru.job4j.meloman.dao.implementation;

import org.apache.log4j.Logger;
import ru.job4j.meloman.dao.FactoryDao;
import ru.job4j.meloman.dao.UserDAO;
import ru.job4j.meloman.entity.Address;
import ru.job4j.meloman.entity.MusicType;
import ru.job4j.meloman.entity.Role;
import ru.job4j.meloman.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User DAO implementation.
 */
public class UserManager implements UserDAO {
    private static final Logger LOG = Logger.getLogger(UserManager.class);

    /**
     * Add the user to a database.
     * @param model User entity to add.
     * @return true.
     */
    @Override
    public boolean add(User model) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.ADD.query)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getLogin());
            statement.setString(3, model.getPassword());
            statement.setString(4, model.getRole().getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't add new user", e);
        }
        return true;
    }

    /**
     * Add the user to a database including all its entities.
     * @param user User entity to add.
     * @return true.
     */
    @Override
    public boolean addFullUser(User user) {
        int id = -1;
        ResultSet rset = null;
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.ADD.query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().getName());
            rset = statement.executeQuery();
            while (rset.next()) {
                id = rset.getInt("id");
            }
            user.setId(id);
            FactoryDao.getInstance().getAddressDao().add(this.createUserAdr(user));
            this.createMusicList(user);
        } catch (SQLException e) {
            LOG.error("Can't add new user", e);
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return true;
    }

    /**
     * Delete the user from a database.
     * @param model User entity to delete.
     * @return true.
     */
    @Override
    public boolean remove(User model) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.DELETE.query)) {
            statement.setInt(1, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't delete user", e);
        }
        return true;
    }

    /**
     * Update the user.
     * @param data Data to update the user.
     * @return true.
     */
    @Override
    public boolean update(User data) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.UPDATE.query)) {
            statement.setString(1, data.getName());
            statement.setString(2, data.getLogin());
            statement.setString(3, data.getPassword());
            statement.setString(4, data.getRole().getName());
            statement.setInt(5, data.getId());
            statement.executeUpdate();
            FactoryDao.getInstance().getAddressDao().update(data.getAddress());
            this.createMusicList(data);
        } catch (SQLException e) {
            LOG.error("Can't update user", e);
        }
        return true;
    }

    /**
     * get the user by its id.
     * @param id User's id.
     * @return Found user.
     */
    @Override
    public User findById(int id) {
        ResultSet rset = null;
        User user = User.EMPTY;
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.GET_BY_ID.query)) {
            statement.setInt(1, id);
            rset = statement.executeQuery();
            while (rset.next()) {
                user = new User(id, rset.getString("u_name"));
                user.setLogin(rset.getString("login"));
                user.setPassword(rset.getString("password"));
                user.setRole(new Role(rset.getInt("id"), rset.getString("r_name")));
                user.setAddress(this.getAddress(user));
                user.setMusicType(this.getMusicType(user));
            }
        } catch (SQLException e) {
            LOG.error("Can't update user", e);
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    LOG.error("Can't close ResSet", e);
                }
            }
        }
        return user;
    }

    /**
     * Get all users from a database.
     * @return List of users.
     */
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        ResultSet rset = null;
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             Statement statement = conn.createStatement()) {
            rset = statement.executeQuery(SQLQuery.GET_ALL.query);
            while (rset.next()) {
                User user = new User(rset.getInt("u_id"), "u_name");
                user.setLogin(rset.getString("login"));
                user.setPassword(rset.getString("password"));
                user.setRole(new Role(rset.getInt("r_id"), rset.getString("r_name")));
                result.add(user);
            }
        } catch (SQLException e) {
            LOG.error("Can't find all users", e);
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    LOG.error("Can't close ResSet", e);
                }
            }
        }
        return result;
    }

    /**
     * Get user's role.
     * @param user User to find role.
     * @return user's role.
     */
    @Override
    public Role getRole(User user) {
        Role role = Role.EMPTY;
        ResultSet rset = null;
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.GET_ROLE.query)) {
            statement.setInt(1, user.getId());
            rset = statement.executeQuery();
            while (rset.next()) {
                role = new Role(rset.getInt("id"), rset.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error("Can't get roles", e);
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
     * Get user's address.
     * @param user User to find address.
     * @return user's address.
     */
    @Override
    public Address getAddress(User user) {
        Address address = Address.EMPTY;
        ResultSet rset = null;
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.GET_ADDRESS.query)) {
            statement.setInt(1, user.getId());
            rset = statement.executeQuery();
            while (rset.next()) {
                String city = rset.getString("city");
                String street = rset.getString("street");
                int house = rset.getInt("house");
                String fullAdr = String.format("%s, %s, %d", city, street, house);
                address = new Address(rset.getInt("id"), fullAdr);
                address.setCity(city);
                address.setStreet(street);
                address.setHouse(house);
                address.setUserId(user.getId());
            }
        } catch (SQLException e) {
            LOG.error("Can't get address", e);
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
     * Get user's music types.
     * @param user User to find music types.
     * @return Set of music types.
     */
    @Override
    public Set<MusicType> getMusicType(User user) {
        Set<MusicType> music = new HashSet<>();
        ResultSet rset = null;
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.GET_USER_MUSIC.query)) {
            statement.setInt(1, user.getId());
            rset = statement.executeQuery();
            while (rset.next()) {
                music.add(new MusicType(rset.getInt("id"), rset.getString("name")));
            }
        } catch (SQLException e) {
            LOG.error("Can't get musicType", e);
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    LOG.error("Can't close ResSet", e);
                }
            }
        }
        return music;
    }

    /**
     * Get a list of users including all its entities.
     * @return List of users.
     */
    @Override
    public List<User> getAllEntities() {
        List<User> result = new ArrayList<>();
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rset = statement.executeQuery(SQLQuery.GET_ALL.query)) {
            while (rset.next()) {
                User user = new User(rset.getInt("u_id"), rset.getString("u_name"));
                user.setLogin(rset.getString("login"));
                user.setPassword(rset.getString("password"));
                user.setRole(this.getRole(user));
                user.setAddress(this.getAddress(user));
                user.setMusicType(this.getMusicType(user));
                result.add(user);
            }
        } catch (SQLException e) {
            LOG.error("Can't get all entities", e);
        }
        return result;
    }

    /**
     * Create a full address for the user.
     * @param user The user to create an address.
     * @return user's address.
     */
    private Address createUserAdr(User user) {
        Address result = new Address();
        result.setCity(user.getAddress().getCity());
        result.setStreet(user.getAddress().getStreet());
        result.setHouse(user.getAddress().getHouse());
        result.setUserId(user.getId());
        return result;
    }

    /**
     * Create music types list for the user.
     * @param user The user to create a music list.
     */
    private void createMusicList(User user) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.CREATE_MUSIC_LIST.query)) {
            for (MusicType music : user.getMusicType()) {
                statement.setInt(1, user.getId());
                statement.setString(2, music.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error("Can't create musicList", e);
        }
    }

    /**
     * Enum of the queries.
     */
    enum SQLQuery {

        ADD("INSERT INTO users(name, login, password, role_id)"
                + " VALUES (?, ?, ?, (SELECT id FROM role WHERE name = ?)) RETURNING id"),
        UPDATE("UPDATE users SET name = ?, login = ?, password = ?, role_id = (SELECT id FROM role WHERE name = ?)"
                + "WHERE id = ?"),
        DELETE("DELETE FROM users WHERE id = ?"),
        GET_BY_ID("SELECT u.name AS u_name, u.login, u.password, r.id, r.name AS r_name  FROM users AS u, role AS r"
                + " WHERE r.id = u.role_id AND u.id = ?"),
        GET_ALL("SELECT u.id AS u_id, u.name AS u_name, u.login, u.password, r.id AS r_id, r.name AS r_name"
                + " FROM users AS u, role AS r WHERE r.id = u.role_id ORDER BY u.id"),
        GET_ROLE("SELECT r.id, r.name FROM users AS u, role AS r WHERE u.role_id = r.id AND u.id = ?"),
        GET_ADDRESS("SELECT a.id, a.city, a.street, a.house FROM address AS a, users AS u WHERE u.id = a.user_id "
                + "AND u.id = ?"),
        GET_USER_MUSIC("SELECT mus.id, mus.name FROM music_type AS mus, meloman AS mel WHERE mus.id = mel.music_type_id"
                + " AND mel.user_id = ?"),
        CREATE_MUSIC_LIST("INSERT INTO meloman (user_id, music_type_id) "
                + "VALUES (?, (SELECT id FROM music_type WHERE name = ?))");

        private String query;

        SQLQuery(String query) {
            this.query = query;
        }
    }
}
