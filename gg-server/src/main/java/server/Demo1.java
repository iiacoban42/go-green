package server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Demo1 {

    /**
     * Database demo.
     * @param args arguments
     */
    public static void main( String[] args ) {
        User user = new User();
        user.setEmail("test@student.tudelft.nl");
        user.setFriend("Andy");
        user.setHashPassword("2x1C&Jkt1ckQ");
        user.setUsername("Test");

        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
            .configure() // configures settings from hibernate.cfg.xml
            .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(user);
        tx.commit();
        session.close();
    }
}
