package ru.job4j.todolist.persistent;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Util which obtains and closes SessionFactory instance.
 */
public class HibernateUtil {
    private static final SessionFactory INSTANCE = new Configuration().configure().buildSessionFactory();

    private HibernateUtil() { }

    public static SessionFactory getSessionInstance() {
        return INSTANCE;
    }

    public static void closeFactory() {
        INSTANCE.close();
    }
}
