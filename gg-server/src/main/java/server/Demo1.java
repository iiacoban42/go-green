package server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 */
public class Demo1 {

    public static void main( String[] args ){
        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setEmail("elfjwcho@fvkjrbw.net");
        user.setFreind("Jcbkva");
        user.setHashPassword("jkgvi");
        user.setUsername("lvberhkb");
        session.save(user);
        tx.commit();
        session.close();
    }
}
