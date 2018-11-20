package ru.job4j.carstore.model;

public class Transmission {
    private int id;
    private String type;
    private byte gears;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte getGears() {
        return gears;
    }

    public void setGears(byte gears) {
        this.gears = gears;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else {
            if (o != null && getClass() == o.getClass()) {
                Transmission tr = (Transmission) o;
                result = this.id == tr.id && this.type.equals(tr.type)
                        && this.gears == tr.gears;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id;
        result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", gears=" + gears +
                '}';
    }
}
