package ru.job4j.meloman.entity;

/**
 * Address model.
 */
public class Address extends Entity {
    public final static Address EMPTY = new Address();
    private String city;
    private String street;
    private int house;
    private int userId;

    public Address() { }

    public Address(int id, String name) {
        super(id, name);
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return this.house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Address{}";
    }
}
