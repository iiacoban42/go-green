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
     * adds a badge to user or modifies the level so that user has badge asked for.
     * @param badgeName name of the badge alocated
     * @param username primarykey/username of user
     * @param level integer representing the level of the badge
     * @return long representing primary key of badge
     */
    public static long addBadge(
            String badgeName,
            String username,
            int level
    ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Badge badge = null;

        try {
            tx = session.beginTransaction();
            String hql = "FROM Badge WHERE badgeName = :badgeName AND user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("badgeName", badgeName);
            badge = (Badge)query.uniqueResult();
            if (badge == null) {
                badge = new Badge(badgeName, username, level);
                session.save(badge);
            } else {
                badge.setLevel(level);
                session.update(badge);
            }
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
            String hql = "FROM Badge WHERE badge";
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
    public static List<Badge> listBadgesUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Badge badge = null;
        List<Badge> results = null;

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

    /**
     * checkd for badges to be alocated for specific user.
     * @param username username of user who needs to have his badge allocated
     */
    public static void checkBadges(String username) {
        checkStreak(username, "vegiemeal");
        checkStreak(username, "transport");
        checkStreak(username, "localProduce");
        checkCo2Badge(username, "vegiemeal");
        checkCo2Badge(username, "transport");
        checkCo2Badge(username, "localProduce");
    }

    /**
     * gives badge to given user and saves it to database.
     * give badges for maintaining daily streak at 7,14,21.
     * @param username a string representing username/primary key of user
     * @param actionName a string representing name of action
     */
    private static void checkStreak(String username, String actionName) {
        int streak = ActionManager.streakCalculator(username, actionName);
        if (streak >= 7) {
            addBadge(actionName  + "StreakBadge", username, 1);
        }
        if (streak >= 14) {
            addBadge(actionName + "StreakBadge", username, 2);
        }
        if (streak >= 21) {
            addBadge(actionName + "StreakBadge", username, 3);
        }
    }

    /**
     * gives badge to given user and saves it to database.
     * gives badge for saving an amount of co2 with a given action.
     * levels are 500000, 1000000, 2000000.
     * @param username a string representing username/primary key of user
     * @param actionName a string representing name of action
     */
    private static void checkCo2Badge(String username, String actionName) {
        long co2Saved = ActionManager.co2SavedByActionByUser(username, actionName);
        if (co2Saved >= 500000) {
            addBadge(actionName  + "Co2SavedBadge", username, 1);
        }
        if (co2Saved >= 1000000) {
            addBadge(actionName + "Co2SavedBadge", username, 2);
        }
        if (co2Saved >= 2000000) {
            addBadge(actionName + "Co2SavedBadge", username, 3);
        }
    }
}
