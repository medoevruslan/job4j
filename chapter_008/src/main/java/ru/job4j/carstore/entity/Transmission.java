package ru.job4j.carstore.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "transmissions")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "gears")
    private Byte gears;

    public Transmission() {
    }

    public Transmission(Integer id) {
        this.id = id;
    }

    public Transmission(String type, byte gears) {
        this.type = type;
        this.gears = gears;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Byte getGears() {
        return this.gears;
    }

    public void setGears(Byte gears) {
        this.gears = gears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transmission that = (Transmission) o;
        return this.id == that.id
                && this.gears == that.gears
                && Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.type, this.gears);
    }

    @Override
    public String toString() {
        return "Transmission{"
                + "id=" + id
                + ", type='" + type + '\''
                + ", gears=" + gears
                + '}';
    }
}
