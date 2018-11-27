package ru.job4j.carstore.dao.implementation;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.carstore.HibernateUtil;
import ru.job4j.carstore.dao.DAO;

import java.util.List;
import java.util.function.Function;

/**
 * Universal Entity Manager works with any entity.
 * @param <E> Entity.
 */
public class Manager<E> implements DAO<E> {
    private static final Logger LOG = Logger.getLogger(Manager.class);

    private Class<E> entity;

    public Manager(Class<E> entity) {
        this.entity = entity;
    }

    /**
     * Method adds entity to database.
     * @param entity Entity to add.
     * @return Id of added entity.
     */
    @Override
    public int add(E entity) {
        return (int) this.tx(session -> session.save(entity));
    }


    /**
     * Updates the entity.
     * @param entity Data to update entity.
     */
    @Override
    public void update(E entity) {
        this.tx(session -> {
            session.update(entity);
            return null;
        });
    }

    /**
     * Removes the entity.
     * @param entity Entity to remove.
     */
    @Override
    public void delete(E entity) {
        this.tx(session -> {
            session.delete(entity);
            return null;
        });
    }

    /**
     * Finds the entity by id.
     * @param id The id of the entity.
     * @return Entity.
     */
    @Override
    public E findById(int id) {
        return this.tx(session -> session.get(this.entity, id));
    }

    /**
     * Finds all entities.
     * @return List of entities.
     */
    @Override
    public List<E> findAll() {
        return this.tx(session -> session.createQuery(String.format("from %s", this.entity.getName())).list());
    }

    /**
     * Removes all entities.
     */
    @Override
    public void deleteAll() {
        this.tx(session -> session.createQuery(String.format("delete from %s", this.entity.getName()))
                .executeUpdate());
    }

    /**
     * Function makes the crud methods succinct.
     * @param command Function.
     * @param <S> Return parameter.
     * @return Outcome applied function.
     */
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
