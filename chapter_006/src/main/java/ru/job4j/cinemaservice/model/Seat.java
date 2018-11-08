package ru.job4j.cinemaservice.model;

/**
 * Seat model.
 */
public class Seat extends Model {
    private int accountId;
    private int price;
    private int rowNum;
    private int seatNum;

    public Seat(int id, String name) {
        super(id, name);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
