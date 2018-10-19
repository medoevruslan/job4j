package ru.job4j.meloman.entity;

/**
 * Music type model.
 */
public class MusicType extends Entity {
    public final static MusicType EMPTY = new MusicType();

    public MusicType() { }

    public MusicType(String name) {
        super(name);
    }

    public MusicType(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "MusicType{}";
    }
}
