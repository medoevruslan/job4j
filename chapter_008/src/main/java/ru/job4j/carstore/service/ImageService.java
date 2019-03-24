package ru.job4j.carstore.service;

import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.dao.implementation.Manager;
import ru.job4j.carstore.entity.Image;

import java.util.List;

public class ImageService implements DAO<Image> {
    private static final DAO INSTANCE = new ImageService();
    private final Manager<Image> manager = new Manager<>(Image.class);

    private ImageService() { }

    public static DAO getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(Image entity) {
        return this.manager.add(entity);
    }

    @Override
    public void update(Image entity) {
        this.manager.update(entity);
    }

    @Override
    public void delete(Image entity) {
        this.manager.delete(entity);
    }

    @Override
    public Image findById(int id) {
        return this.manager.findById(id);
    }

    @Override
    public List<Image> findAll() {
        return this.manager.findAll();
    }

    @Override
    public void deleteAll() {
        this.manager.deleteAll();
    }
}
