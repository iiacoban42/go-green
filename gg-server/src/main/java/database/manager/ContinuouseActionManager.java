package database.manager;

import database.entity.ContinuouseAction;
import database.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.Iterator;
import java.util.List;





public class ContinuouseActionManager {
    /**
     * creates a continuous action and comitis it to database.
     * @param username a String representing the primarykey/username of user
     * @param actionName a String represening the type of continuouse action
     * @param scorePerDay an int representing the score gained per day.
     * @param relavantInfo an int that can represent what is needed such as # of solar panels
     * @return a long that represents CAs primary key
     */
    public static long createCa(String username, String actionName,
                                int scorePerDay, int relavantInfo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ContinuouseAction continuouseAction = null;

        try {
            tx = session.beginTransaction();
            continuouseAction = new ContinuouseAction(username, actionName,
                    scorePerDay, relavantInfo);
            session.save(continuouseAction);
            User user = UserManager.getUser(continuouseAction.getUser());
            user.addScore(scorePerDay);
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return continuouseAction.getId();
    }

    /**
     * delets Continuouse action from database.
     * @param id a long representing primary key of continuouse action
     */
    public static void deleteCa(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ContinuouseAction continuouseAction =
                    (ContinuouseAction)session.get(ContinuouseAction.class, id);
            session.delete(continuouseAction);
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
     * performs all updates required for "check in" including updating user score.
     * @param id a long representing primary key of continuouse action
     */
    public static void checkInCa(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ContinuouseAction continuouseAction =
                    (ContinuouseAction)session.get(ContinuouseAction.class, id);
            continuouseAction.checkIn();
            continuouseAction.setDateLastCashedIn(new Date());
            session.update(continuouseAction);
            tx.commit();
            String username = continuouseAction.getUser();
            UserManager.addScore(username, continuouseAction.getScorePerDay());
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
     * gets ContinuouseAction with primary key id.
     * @param id a long representing primary key of continuouse action
     * @return a ContinuouseAction with primary key id
     */
    public static ContinuouseAction getCa(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ContinuouseAction continuouseAction = null;

        try {
            tx = session.beginTransaction();
            continuouseAction = (ContinuouseAction)session.get(ContinuouseAction.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return continuouseAction;
    }

    /**
     * lists all continuous actions in database.
     * @return a List of ContinuouseAction in database
     */
    public static List<ContinuouseAction> listCa() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<ContinuouseAction> continuouseActions = null;

        try {
            tx = session.beginTransaction();
            continuouseActions = session.createQuery("From ContinuouseAction").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return continuouseActions;

    }

    /**
     * returns a list of all active continouse Actions of user.
     * @param username a String representing the primarykey/username of user
     * @return a List of Continuousection
     */
    public static List<ContinuouseAction> listActiveCaByUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<ContinuouseAction> results = null;

        try {
            tx = session.beginTransaction();
            String hql = "FROM ContinuouseAction WHERE user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            results = query.list();
            tx.commit();
            Iterator<ContinuouseAction> itr = results.iterator();
            while (itr.hasNext()) {
                ContinuouseAction continuouseAction = itr.next();
                if (continuouseAction.getDateEnded() != null) {
                    itr.remove();
                }
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    /**
     * puts an end date on given ContinuouseAction.
     * @param id a long representing primary key of continuouse action
     */
    public static void endCa(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ContinuouseAction continuouseAction =
                    (ContinuouseAction)session.get(ContinuouseAction.class, id);
            continuouseAction.setDateEnded(new Date());
            session.update(continuouseAction);
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
}
