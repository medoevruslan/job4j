package ru.job4j.carstore;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final Logger LOG = Logger.getLogger(HibernateUtil.class);

    private final static SessionFactory INSTANCE;

    private HibernateUtil() { }

    static {
        try {
            INSTANCE = new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            LOG.error("Can't instantiate SessionFactory", ex);
            throw ex;
        }
    }

    public static SessionFactory getSessionInstance() {
        return INSTANCE;
    }
}
