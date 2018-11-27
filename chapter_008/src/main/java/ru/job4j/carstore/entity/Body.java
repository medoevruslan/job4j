package ru.job4j.carstore.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bodies")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "color")
    private String color;

    public Body() {
    }

    public Body(Integer id) {
        this.id = id;
    }

    public Body(String type, String color) {
        this.type = type;
        this.color = color;
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

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        return this.id == body.id
                && Objects.equals(this.type, body.type)
                && Objects.equals(this.color, body.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.type, this.color);
    }

    @Override
    public String toString() {
        return "Body{"
                + "id=" + id
                + ", type='" + type + '\''
                + ", color='" + color + '\''
                + '}';
    }
}
