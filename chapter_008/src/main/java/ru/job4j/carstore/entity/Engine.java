package ru.job4j.carstore.entity;


import javax.persistence.*;

@Entity
@Table(name = "engines")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "capacity")
    private Float capacity;

    public Engine() {
    }

    public Engine(Integer id) {
        this.id = id;
    }

    public Engine(float capacity) {
        this.capacity = capacity;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getCapacity() {
        return this.capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else if (o != null && getClass() == o.getClass()) {
            Engine engine = (Engine) o;
            result = this.id == engine.id && this.capacity == engine.capacity;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id;
        result = 31 * result + Float.floatToIntBits(this.capacity);
        return result;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "id=" + id
                + ", capacity=" + capacity
                + '}';
    }
}
