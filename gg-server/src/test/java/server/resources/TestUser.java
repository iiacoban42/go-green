package server.resources;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import server.User;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestUser {

    @Test
    public void testAddUser(){
        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setEmail("elfjwcho@fvkjrbw.net");
        user.setHashPassword("jkgvi");
        user.setUsername("lvberhkb");
        session.save(user);
        tx.commit();

        List<User> users = session.createQuery("from User").list();
        assertEquals(users.get(0).getUsername(), "lvberhkb");

        User user2 = (User) session.get(User.class, "lvberhkb");

        session.delete(user);
        session.close();
    }


}
