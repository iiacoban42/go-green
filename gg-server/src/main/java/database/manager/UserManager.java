package database.manager;

import database.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserManager {
    /**
     * Method to create a User in the database.
     * @return the user that has been created,
     *      null if add failed
     */
    public static User addUser(
            final String username,
            final String password,
            final String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        User user = null;

        try {
            tx = session.beginTransaction();
            user = new User(username, password, email);
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
        return user;
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
            session.delete(user);
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
    public static User getUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        User user = null;

        try {
            tx = session.beginTransaction();
            user = (User)session.get(User.class, username);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Method to change user password.
     * @param username primary key, String represents users username
     * @param hashPassword String representing hashed password to be set
     * @return User with updated hashPassword
     */
    public static User changePassword(String username, String hashPassword){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        User user = null;

        try {
            tx = session.beginTransaction();
            user = (User)session.get(User.class, username);
            user.setHashPassword(hashPassword);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

}

