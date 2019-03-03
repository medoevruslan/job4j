package ru.job4j.carstore.dao.implementation;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.carstore.HibernateUtil;
import ru.job4j.carstore.dao.ItemDAO;
import ru.job4j.carstore.entity.Item;

import java.util.List;
import java.util.function.Function;

public class ItemManager implements ItemDAO {
    private static final Logger LOG = Logger.getLogger(ItemManager.class);

    @Override
    public int add(Item entity) {
        return (int) this.tx(session -> session.save(entity));
    }

    @Override
    public void update(Item entity) {
        this.tx(session -> {
            session.update(entity);
            return null;
        });
    }

    @Override
    public void delete(Item entity) {
        this.tx(session -> {
           session.delete(entity);
           return null;
        });
    }

    @Override
    public Item findById(int id) {
        return this.tx(session -> session.get(Item.class, id));
    }

    @Override
    public List<Item> findAll() {
        return this.tx(session -> session.createQuery("from Item ").list());
    }

    @Override
    public void deleteAll() {
        this.tx(session -> {
           session.createQuery("delete from Item").executeUpdate();
           return null;
        });
    }

    public void setSold(int id, boolean sold) {
        this.tx(session -> {
            Item item = session.load(Item.class, id);
            item.setSold(sold);
            return null;
        });
    }

    private <S> S tx(Function<Session, S> command) {
        Session session = HibernateUtil.getSessionInstance().openSession();
        Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (Exception ex) {
            tx.rollback();
            LOG.error("Can't apply session", ex);
            throw ex;
        } finally {
            tx.commit();
            session.close();
        }
    }
}
