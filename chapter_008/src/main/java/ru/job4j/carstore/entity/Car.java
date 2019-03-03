package ru.job4j.carstore.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "engine")
    private String engine;

    @Column(name = "body")
    private String body;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Car(String manufacturer, String model, String transmission, String engine, String body) {
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

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getTransmission() {
        return this.transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return this.engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
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
                && Objects.equals(this.model, car.model)
                && Objects.equals(this.transmission, car.transmission)
                && Objects.equals(this.engine, car.engine)
                && Objects.equals(this.body, car.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.manufacturer, this.model, this.transmission, this.engine, this.body);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", manufacturer=" + manufacturer
                + ", model=" + model
                + ", transmission=" + transmission
                + ", engine=" + engine
                + ", body=" + body
                + '}';
    }
}
