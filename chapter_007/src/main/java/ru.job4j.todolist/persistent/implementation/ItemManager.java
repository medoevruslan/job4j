package ru.job4j.todolist.persistent.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.persistent.HibernateUtil;
import ru.job4j.todolist.persistent.ItemRepository;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Implementation of ItemRepository.
 */
public class ItemManager implements ItemRepository {
    private final SessionFactory factory = HibernateUtil.getSessionInstance();

    /**
     * Adds item to database.
     * @param item Item to add.
     */
    @Override
    public void add(Item item) {
        this.tx(session -> session.save(item));
    }

    /**
     * Updates Item in database.
     * @param item Data to update Item.
     */
    @Override
    public void update(Item item) {
        this.tx(session -> {
            Item it = session.load(Item.class, item.getId());
            it.setDone(item.isDone());
        });
    }

    /**
     * Removes Item from database.
     * @param item Item to remove.
     */
    @Override
    public void delete(Item item) {
        this.tx(session -> session.delete(item));
    }

    /**
     * Receives all Items from database.
     * @return List of Items.
     */
    @Override
    public List<Item> findAll() {
        return this.getItems(
                session -> session.createQuery("from Item ").list());
    }

    /**
     * Receives all Items which are uncompleted.
     * @return List of Items.
     */
    @Override
    public List<Item> findUndone() {
        return this.getItems(
                session -> session.createQuery("from Item where done = false ").list());
    }

    private <T> T getItems(final Function<Session, T> command) {
        final Session session = this.factory.openSession();
        session.beginTransaction();
        try {
            return command.apply(session);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    private void tx(final Consumer<Session> command) {
        final Session session = this.factory.openSession();
        session.beginTransaction();
        try {
            command.accept(session);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
