package ru.job4j.cinemaservice.model;

/**
 * Hall model.
 */
public class Hall extends Model {
    private int seats;
    private int rows;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }

    public Hall(int id, String name) {
        super(id, name);
    }
}
