package ru.job4j.carstore.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "manufacturer")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "transmission")
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "engine")
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "body")
    private Body body;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Car(Manufacturer manufacturer, Transmission transmission, Engine engine, Body body) {
        this.manufacturer = manufacturer;
        this.transmission = transmission;
        this.engine = engine;
        this.body = body;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Transmission getTransmission() {
        return this.transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return this.id == car.id
                && Objects.equals(this.manufacturer, car.manufacturer)
                && Objects.equals(this.transmission, car.transmission)
                && Objects.equals(this.engine, car.engine)
                && Objects.equals(this.body, car.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.manufacturer, this.transmission, this.engine, this.body);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", manufacturer=" + manufacturer
                + ", transmission=" + transmission
                + ", engine=" + engine
                + ", body=" + body
                + '}';
    }
}
