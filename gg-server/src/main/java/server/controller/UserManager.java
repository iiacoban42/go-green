package server.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import server.entity.User;

import java.util.List;

public class UserManager {
    /**
     * Method to create a User in the database.
     */
    public static String addUser(
            final String username,
            final String password,
            final String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = new User(username, password, email);
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return username;
    }

    /**
     * Method to list all users in database.
     * @return a list containing all users in database
     */
    public static List listUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List users = null;

        try {
            tx = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            return users;
        }
    }

    /**
     * Method to deleat a user from the database.
     */
    public static void deleteUser(final String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = (User)session.get(User.class, username);
            session.delete(username);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Mothod to get user by username.
     * @param username a Sting representingthe username /primarykey of user
     * @return a List containing only the relevant user
     */
    public static User getUsers(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List results = null;

        try {
            tx = session.beginTransaction();
            String hql = "FROM User WHERE username = " + username;
            Query query = session.createQuery(hql);
            results = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        if(results.size() == 1){
            return (User) results.get(0);
        }
        throw new Error();
    }
}

