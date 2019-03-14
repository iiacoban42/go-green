package database.manager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import server.controller.HibernateUtil;

import java.util.List;

public class ActionManager {

    /**
     * Cretes a new action and adds it to the database
     * @param actionName
     * @param user
     * @param score
     * @return
     */
    public static Action addAction(
            String actionName,
            String user,
            int score
    ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        return action;
    }

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

    public static void deleteAction(int id) {
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

    public static Action getAction(int id) {
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


}
