package ru.job4j.todolist.persistent.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.persistent.HibernateUtil;
import ru.job4j.todolist.persistent.ItemRepository;

import java.util.List;

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
        Session session = this.factory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Updates Item in database.
     * @param item Data to update Item.
     */
    @Override
    public void update(Item item) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Item it = session.load(Item.class, item.getId());
        it.setDone(item.isDone());
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Removes Item from database.
     * @param item Item to remove.
     */
    @Override
    public void delete(Item item) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Receives all Items from database.
     * @return List of Items.
     */
    @Override
    public List<Item> findAll() {
        Session session = this.factory.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Receives all Items which are uncompleted.
     * @return List of Items.
     */
    @Override
    public List<Item> findUndone() {
        Session session = this.factory.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item where done = false ").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
