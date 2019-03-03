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

    public Transmission() {
    }

    public Transmission(Integer id) {
        this.id = id;
    }

    public Transmission(String type) {
        this.type = type;
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
                && Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.type);
    }

    @Override
    public String toString() {
        return "Transmission{"
                + "id=" + id
                + ", type='" + type + '\''
                + '}';
    }
}
