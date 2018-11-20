package ru.job4j.carstore.model;

public class Engine {
    private int id;
    private int horsePower;
    private float engineCapacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public float getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else {
            if (o != null && getClass() == o.getClass()) {
                Engine engine = (Engine) o;
                result = this.id == engine.id && this.engineCapacity == engine.engineCapacity
                        && this.horsePower == engine.horsePower;
            }
        }
        return  result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id;
        result = 31 * result + this.horsePower;
        result = 31 * result + Float.floatToIntBits(this.engineCapacity);
        return result;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", horsePower=" + horsePower +
                ", engineCapacity=" + engineCapacity +
                '}';
    }
}
