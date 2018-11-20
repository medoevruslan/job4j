package ru.job4j.carstore.model;

public class Body {
    private int id;
    private String type;
    private String color;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else {
            if (o != null && getClass() == o.getClass()) {
                Body body = (Body) o;
                result = this.id == body.id && this.type.equals(body.type)
                        && this.color.equals(body.color);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id;
        result = 31 * result + (this.color != null ? this.color.hashCode() : 0);
        result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Body{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
