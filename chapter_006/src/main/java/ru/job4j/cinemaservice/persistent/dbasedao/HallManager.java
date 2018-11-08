package ru.job4j.cinemaservice.persistent.dbasedao;

import org.apache.log4j.Logger;
import ru.job4j.cinemaservice.utils.AutoRollback;
import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;
import ru.job4j.cinemaservice.persistent.DataBase;
import ru.job4j.cinemaservice.persistent.HallDAO;

import java.sql.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Hall DAO.
 */
public class HallManager implements HallDAO {
    private static final Logger LOG = Logger.getLogger(HallManager.class);

    /**
     * Method adds model to database.
     * @param model Model to add.
     * @return Id of added model.
     */
    @Override
    public int add(Hall model) {
        int id = -1;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.ADD.query)) {
            statement.setString(1, model.getName());
            statement.setInt(2, model.getRows());
            statement.setInt(3, model.getSeats());
            ResultSet rset = statement.executeQuery();
            while (rset.next()) {
                id = rset.getInt("id");
            }
            rollback.commit();
            this.closeRSet(rset);
        } catch (SQLException e) {
            LOG.error("Can't add hall", e);
        }
        return id;
    }

    /**
     * Gets all seats of the Hall.
     * @param hall Hall to get the seats.
     * @return HashMap of rows and seats of the Hall.
     */
    @Override
    public HashMap<Integer, ArrayList<Seat>> getSeatsByHall(Hall hall) {
        HashMap<Integer, ArrayList<Seat>> result = new HashMap<>();
        IntStream.rangeClosed(1, hall.getRows()).forEach(row -> result.put(row, this.getSeatsByRow(row)));
        return result;
    }

    /**
     * Updates the model.
     * @param model Model to update.
     * @return Result of update (true or false).
     */
    @Override
    public boolean update(Hall model) {
        boolean result = false;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.UPDATE.query)) {
            statement.setString(1, model.getName());
            statement.setInt(3, model.getId());
            result = statement.executeUpdate() > 0;
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't update hall", e);
        }
        return result;
    }

    /**
     * Removes the model from database.
     * @param model Model to remove.
     * @return Result of remove (true or false).
     */
    @Override
    public boolean remove(Hall model) {
        boolean result = false;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.DELETE.query)) {
            statement.setInt(1, model.getId());
            result = statement.executeUpdate() > 0;
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't delete hall", e);
        }
        return result;
    }

    /**
     * Finds the model by id.
     * @param id Id to find the model.
     * @return Model.
     */
    @Override
    public Optional<Hall> findById(int id) {
        Optional<Hall> result = Optional.empty();
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.FIND_BY_ID.query)) {
            statement.setInt(1, id);
            ResultSet rset = statement.executeQuery();
            while (rset.next()) {
                String name = rset.getString("name");
                result = Optional.of(new Hall(id, name));
            }
            rollback.commit();
            this.closeRSet(rset);
        } catch (SQLException e) {
            LOG.error("Can't find hall by id ", e);
        }
        return result;
    }

    /**
     * Finds all exists models.
     * @return List of models.
     */
    @Override
    public List<Hall> findAll() {
        List<Hall> result = Collections.EMPTY_LIST;
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             Statement statement = conn.createStatement();
             ResultSet rset = statement.executeQuery(Query.FIND_ALL.query)) {
            while (rset.next()) {
                result = new ArrayList<>();
                int id = rset.getInt("id");
                String name = rset.getString("name");
                Hall hall = new Hall(id, name);
                hall.setRows(rset.getInt("rows"));
                hall.setSeats(rset.getInt("seats"));
                result.add(hall);
            }
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't find all halls", e);
        }
        return result;
    }

    /**
     * Fetches seats of the current row.
     * @param row Row to get the seats.
     * @return ArrayList of row's seats.
     */
    private ArrayList<Seat> getSeatsByRow(int row) {
        ArrayList<Seat> result = new ArrayList<>();
        try (Connection conn = DataBase.getInstance().getConnetion();
             AutoRollback rollback = new AutoRollback(conn);
             PreparedStatement statement = conn.prepareStatement(Query.GET_SEATS_BY_ROW.query)) {
            statement.setInt(1, row);
            ResultSet rset = statement.executeQuery();
            while (rset.next()) {
                int id = rset.getInt("id");
                int number = rset.getInt("number");
                String name = String.format("Row %s, Seat %s", row, number);
                Seat seat = new Seat(id, name);
                seat.setRowNum(row);
                seat.setSeatNum(number);
                seat.setPrice(rset.getInt("price"));
                seat.setAccountId(rset.getInt("account_id"));
                result.add(seat);
            }
            this.closeRSet(rset);
            rollback.commit();
        } catch (SQLException e) {
            LOG.error("Can't get seats by hall", e);
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

        ADD("INSERT INTO halls(name, rows, seats) VALUES(?, ?, ?) RETURNING id"),
        UPDATE("UPDATE halls SET name = ? WHERE id = ?"),
        DELETE("DELETE FROM halls WHERE id = ?"),
        FIND_BY_ID("SELECT * FROM halls WHERE id = ?"),
        FIND_ALL("SELECT * FROM halls"),
        GET_SEATS_BY_ROW("SELECT * FROM seats WHERE row = ?");

        private String query;

        Query(String query) {
            this.query = query;
        }
    }
}
