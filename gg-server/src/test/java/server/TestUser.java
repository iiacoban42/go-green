package server;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

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
        server.entity.User user = new server.entity.User();
        user.setEmail("elfjwcho@fvkjrbw.net");
        user.setHashPassword("jkgvi");
        user.setUsername("lvberhkb");
        session.save(user);
        tx.commit();

        List<server.entity.User> users = session.createQuery("from User").list();
        assertEquals(users.get(0).getUsername(), "lvberhkb");

        session.close();
    }


}