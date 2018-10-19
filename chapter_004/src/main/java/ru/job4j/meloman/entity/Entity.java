package ru.job4j.meloman.entity;

/**
 * Abstract entity's model.
 */
public abstract class Entity {
    private int id;
    private String name;

    public Entity() { }

    public Entity(String name) {
        this.name = name;
    }

    public Entity(int id, String name) {
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
        if (this == o) {
            rst = true;
        } else if (o != null && getClass() == o.getClass()) {
            Entity entity = (Entity) o;
            rst = entity.id == this.id && entity.name.equals(this.name);
        }
        return rst;
    }

    @Override
    public int hashCode() {
        int rst = 17;
        rst = 31 * rst + (this.name != null ? this.name.hashCode() : 0);
        rst = 31 * rst + id;
        return rst;
    }
}
