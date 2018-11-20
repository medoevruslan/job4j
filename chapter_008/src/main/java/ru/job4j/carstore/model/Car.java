package ru.job4j.carstore.model;

public class Car {
    private int id;
    private Body body;
    private Engine engine;
    private Transmission transmission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }



    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else {
            if (o != null && getClass() == o.getClass()) {
                Car car = (Car) o;
                result = this.id == car.id && this.body == car.body;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id;
        result = 31 * result + 31 * (this.body != null ? this.body.hashCode() : 0);
        result = 31 * result + 31 * (this.engine != null ? this.engine.hashCode() : 0);
        result = 31 * result + 31 * (this.transmission != null ? this.transmission.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", body=" + body +
                ", engine=" + engine +
                ", transmission=" + transmission +
                '}';
    }
}
