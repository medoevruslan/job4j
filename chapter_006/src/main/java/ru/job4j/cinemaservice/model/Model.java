package ru.job4j.cinemaservice.model;

/**
 * Abstract entity.
 */
public abstract class Model {
    private int id;
    private String name;

    public Model() { }

    public Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        boolean rst = false;
        if (o == this) {
            rst = true;
        } else if (o != null && getClass() == o.getClass()) {
            Model model = (Model) o;
            rst = model.id == this.id && model.name.equals(this.name);
        }
        return rst;
    }

    @Override
    public int hashCode() {
        int rst = 17;
        rst = 31 * rst + this.id;
        rst = 31 * rst + (this.name != null ? this.name.hashCode() : 0);
        return rst;
    }

    @Override
    public String toString() {
        return "Model{"
                + "id=" + id
                + ", name=" + name
                + '}';
    }
}
