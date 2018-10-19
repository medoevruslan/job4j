package ru.job4j.meloman.dao.implementation;

import org.apache.log4j.Logger;
import ru.job4j.meloman.dao.DAO;
import ru.job4j.meloman.dao.FactoryDao;
import ru.job4j.meloman.entity.MusicType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Music type DAO implementation.
 */
public class MusicManager implements DAO<MusicType> {
    private final static Logger LOG = Logger.getLogger(MusicType.class);

    /**
     * Add music type to a database.
     * @param model Music type to add.
     * @return true.
     */
    @Override
    public boolean add(MusicType model) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.ADD.query)) {
            statement.setString(1, model.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't add musicType", e);
        }
        return true;
    }

    /**
     * Delete music type from a database.
     * @param model Music type to remove.
     * @return true.
     */
    @Override
    public boolean remove(MusicType model) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.DELETE.query)) {
            statement.setInt(1, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't delete musicType", e);
        }
        return true;
    }

    /**
     * Update music type in a database.
     * @param data Data to update music type.
     * @return true.
     */
    @Override
    public boolean update(MusicType data) {
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.UPDATE.query)) {
            statement.setString(1, data.getName());
            statement.setInt(2, data.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't update musicType", e);
        }
        return true;
    }

    /**
     * Receive music type by its id.
     * @param id Music type's id.
     * @return found music type.
     */
    @Override
    public MusicType findById(int id) {
        MusicType music = MusicType.EMPTY;
        ResultSet rset = null;
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQLQuery.GET_BY_ID.query)) {
            statement.setInt(1, id);
            rset = statement.executeQuery();
            while (rset.next()) {
                music = new MusicType(id, rset.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error("Can't find musicType", e);
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
     * Receive a list all music types from database.
     * @return music type's list.
     */
    @Override
    public List<MusicType> findAll() {
        List<MusicType> result = new ArrayList<>();
        try (Connection conn = FactoryDao.SOURCE.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rset = statement.executeQuery(SQLQuery.GET_ALL.query)) {
            while (rset.next()) {
                result.add(new MusicType(rset.getInt("id"), rset.getString("name")));
            }
        } catch (SQLException e) {
            LOG.error("Can't get all musicType", e);
        }
        return result;
    }

    /**
     * Enum of queries.
     */
    enum SQLQuery {
        ADD("INSERT INTO music_type (name) VALUES (?)"),
        UPDATE("UPDATE music_type SET name = ? WHERE id = ?"),
        GET_BY_ID("SELECT * FROM music_type WHERE id = ?"),
        GET_ALL("SELECT * FROM music_type"),
        DELETE("DELETE FROM music_type WHERE id = ?");

        private String query;

        SQLQuery(String query) {
            this.query = query;
        }
    }
}

