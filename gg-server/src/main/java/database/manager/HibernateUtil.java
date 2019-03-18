package database.manager;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Makes a running session.
     * @return a running session.
     */
    public static Session getHibernateSession() {

        final SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        // factory = new Configuration().configure().buildSessionFactory();
        final Session session = sf.openSession();
        return session;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}