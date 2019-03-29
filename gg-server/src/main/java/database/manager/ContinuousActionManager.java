package database.manager;

import database.entity.ContinuousAction;
import database.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;





public class ContinuousActionManager {
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
        ContinuousAction continuousAction = null;

        try {
            tx = session.beginTransaction();
            continuousAction = new ContinuousAction(username, actionName,
                    scorePerDay, relavantInfo);
            session.save(continuousAction);
            User user = UserManager.getUser(continuousAction.getUser());
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
        return continuousAction.getId();
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
            ContinuousAction continuousAction =
                    (ContinuousAction)session.get(ContinuousAction.class, id);
            session.delete(continuousAction);
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
    public static void cashInCa(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ContinuousAction continuousAction =
                    (ContinuousAction)session.get(ContinuousAction.class, id);
            continuousAction.chashIn();
            continuousAction.setDateLastCashedIn(new Date());
            session.update(continuousAction);
            tx.commit();
            String username = continuousAction.getUser();
            UserManager.addScore(username, continuousAction.getScorePerDay());
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
     * gets ContinuousAction with primary key id.
     * @param id a long representing primary key of continuouse action
     * @return a ContinuousAction with primary key id
     */
    public static ContinuousAction getCa(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ContinuousAction continuousAction = null;

        try {
            tx = session.beginTransaction();
            continuousAction = (ContinuousAction)session.get(ContinuousAction.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return continuousAction;
    }

    /**
     * lists all continuous actions in database.
     * @return a List of ContinuousAction in database
     */
    public static List<ContinuousAction> listCa() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<ContinuousAction> continuousActions = null;

        try {
            tx = session.beginTransaction();
            continuousActions = session.createQuery("From ContinuousAction").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return continuousActions;

    }

    /**
     * returns a list of all active continouse Actions of user.
     * @param username a String representing the primarykey/username of user
     * @return a List of Continuousection
     */
    public static ContinuousAction getActiveCaByUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ContinuousAction continuousAction = null;

        try {
            tx = session.beginTransaction();
            String hql = "FROM ContinuousAction WHERE user = :username AND dateEnded IS NULL";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            continuousAction = (ContinuousAction) query.list().get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return continuousAction;
    }

    /**
     * puts an end date on given ContinuousAction.
     * @param id a long representing primary key of continuouse action
     */
    public static void endCa(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ContinuousAction continuousAction =
                    (ContinuousAction)session.get(ContinuousAction.class, id);
            continuousAction.setDateEnded(new Date());
            session.update(continuousAction);
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
