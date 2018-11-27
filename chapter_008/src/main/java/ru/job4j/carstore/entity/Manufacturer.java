package ru.job4j.carstore.entity;


import javax.persistence.*;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    public Manufacturer() {
    }

    public Manufacturer(Integer id) {
        this.id = id;
    }

    public Manufacturer(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
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

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else if (o != null && getClass() == o.getClass()) {
            Manufacturer mnfrer = (Manufacturer) o;
            result = this.id == mnfrer.id && this.model.equals(mnfrer.model);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id;
        result = 31 * result + (this.model != null ? this.model.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Manufacturer{"
                + "id=" + id
                + ", manufacturer='" + manufacturer + '\''
                + ", model='" + model + '\''
                + '}';
    }
}

