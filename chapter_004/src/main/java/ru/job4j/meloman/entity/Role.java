package ru.job4j.meloman.entity;

/**
 * Role model.
 */
public class Role extends Entity {
    public final static Role EMPTY = new Role();

    public Role() { }

    public Role(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Role{}";
    }
}
