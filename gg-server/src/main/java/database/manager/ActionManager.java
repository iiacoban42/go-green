package database.manager;

import database.entity.Action;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ActionManager {

    /**
     * Cretes a new action and adds it to the database.
<<<<<<< HEAD
     * @param actionName
     * @param user
     * @param score
     * @return
=======
     * @param actionName String representing name of action
     * @param user String representing username/primary key of user
     * @param score integer representing score
     * @return id, a intefer representing the primary key of Actiom
>>>>>>> 28709164224bdc78e08765cee484b62e72ec0d81
     */
    public static long addAction(
            String actionName,
            String user,
            int score
    ) {
        final Session session = HibernateUtil.getHibernateSession();
        Transaction tx = null;
        Action action = null;

        try {
            tx = session.beginTransaction();
            action = new Action(actionName, user, score);
            session.save(action);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return action.getId();
    }

    /**
     * Lists all actions in database.
     * @return a List object containing all Actions in database
     */
    public static List actionList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List actions = null;

        try {
            tx = session.beginTransaction();
            actions = session.createQuery("From Action").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return actions;
    }

    /**
     * Deletes action with given id.
     * @param id an long representing the primary key of the action
     */
    public static void deleteAction(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Action action = (Action)session.get(Action.class, id);
            session.delete(action);
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
     * Retrieves Action with given id.
     * @param id a long representing the primary key of action
     * @return an Actuion, representing the row in the database
     */
    public static Action getAction(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Action action = null;

        try {
            tx = session.beginTransaction();
            action = (Action)session.get(Action.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return action;
    }

    /**
     * Lists all actions performed by user.
     * @param username a String representing the username/primary key of the user
     * @return a List with Action objects
     */
    public static List listActionsUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Action action = null;
        List results = null;

        try {
            tx = session.beginTransaction();
            String hql = "FROM Action WHERE user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
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
        return results;
    }

}
