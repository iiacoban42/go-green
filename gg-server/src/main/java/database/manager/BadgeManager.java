package database.manager;

import database.entity.Badge;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BadgeManager {

    /**
     * Cretes a new badge and adds it to the database.
     * @param badgeName String representing name of badge
     * @param user String representing username/primary key of user
     * @return id, a integer representing the primary key of Badge
     */
    public static long addBadge(
            String badgeName,
            String user

    ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Badge badge = null;

        try {
            tx = session.beginTransaction();
            badge = new Badge(badgeName, user);
            session.save(badge);
            tx.commit();
        } catch (HibernateException e) {
            try {
                tx.rollback();
            } catch (NullPointerException e1) {
                e1.printStackTrace();
            }
        } finally {
            session.close();
        }
        return badge.getId();
    }

    /**
     * Lists all badges in database.
     * @return a List object containing all Badges in database
     */
    public static List badgeList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List badges = null;

        try {
            tx = session.beginTransaction();
            badges = session.createQuery("From Badge").list();
            tx.commit();
        } catch (HibernateException e) {
            try {
                tx.rollback();
            } catch (NullPointerException e1) {
                e1.printStackTrace();
            }
        } finally {
            session.close();
        }
        return badges;
    }

    /**
     * Deletes badge with given id.
     * @param id an long representing the primary key of the badge
     */
    public static void deleteBadge(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Badge badge = (Badge)session.get(Badge.class, id);
            session.delete(badge);
            tx.commit();
        } catch (HibernateException e) {
            try {
                tx.rollback();
            } catch (NullPointerException e1) {
                e1.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    /**
     * Retrieves Badge with given id.
     * @param id a long representing the primary key of badge
     * @return a Badge, representing the row in the database
     */
    public static Badge getBadge(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Badge badge = null;

        try {
            tx = session.beginTransaction();
            badge = (Badge)session.get(Badge.class, id);
            tx.commit();
        } catch (HibernateException e) {
            try {
                tx.rollback();
            } catch (NullPointerException e1) {
                e1.printStackTrace();
            }
        } finally {
            session.close();
        }
        return badge;
    }

    /**
     * Lists all badges performed by user.
     * @param username a String representing the username/primary key of the user
     * @return a List with Badge objects
     */
    public static List listBadgesUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Badge badge = null;
        List results = null;

        try {
            tx = session.beginTransaction();
            String hql = "FROM Badge WHERE user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            results = query.list();
            tx.commit();
        } catch (HibernateException e) {
            try {
                tx.rollback();
            } catch (NullPointerException e1) {
                e1.printStackTrace();
            }
        } finally {
            session.close();
        }
        return results;
    }

}
