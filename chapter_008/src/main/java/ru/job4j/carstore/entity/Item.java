package ru.job4j.carstore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Timestamp created;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "sold")
    private Boolean sold;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "items")
    @JsonManagedReference
    private List<Image> images;

    public Item() {
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Item(String header, String description, Timestamp created, User user, Car car, Boolean sold, Integer price) {
        this.header = header;
        this.description = description;
        this.created = created;
        this.user = user;
        this.car = car;
        this.sold = sold;
        this.price = price;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return this.description;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getSold() {
        return this.sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id)
                && Objects.equals(header, item.header)
                && Objects.equals(description, item.description)
                && Objects.equals(user, item.user)
                && Objects.equals(car, item.car)
                && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, header, description, user, car, price);
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", header='" + header + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + ", user=" + user
                + ", car=" + car
                + ", sold=" + sold
                + ", price=" + price
                + '}';
    }
}
