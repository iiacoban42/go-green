package database.manager;

import database.entity.Action;
import database.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ActionManager {

    /**
     * Cretes a new action and adds it to the database.
     * @param actionName String representing name of action
     * @param username String representing username/primary key of user
     * @param score integer representing score
     * @return id, a intefer representing the primary key of Actiom
     */
    public static long addAction(
            String actionName,
            String username,
            int score
    ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Action action = null;

        try {
            tx = session.beginTransaction();
            action = new Action(actionName, username, score);
            session.save(action);
            User user = (User)session.get(User.class, username);
            user.addScore(score);
            session.update(user);
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
            try {
                tx.rollback();
            } catch (NullPointerException e1) {
                e1.printStackTrace();
            }
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
            User user = (User)session.get(User.class, action.getUser());
            user.settotalScore(user.gettotalScore() - action.getScore());
            session.update(user);
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
            try {
                tx.rollback();
            } catch (NullPointerException e1) {
                e1.printStackTrace();
            }
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
    public static List<Action> listActionsUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Action action = null;
        List<Action> results = null;

        try {
            tx = session.beginTransaction();
            String hql = "FROM Action WHERE user = :username";
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
     * counts amount of consecutive days user has done vegimeal action.
     * @param username username of user
     * @return integer represening streaj
     */
    public static int vegimealStreak(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        final long millisInDay = 86400000;
        int streak = 0;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Action WHERE actionName = :actionName AND user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("actionName", "vegimeal");
            List<Action> results = query.list();
            long time = System.currentTimeMillis();
            for (Action action : results) {
                if (action.getDateTime().getTime() > time - millisInDay) {
                    streak++;
                    break;
                }
            }
            time -= millisInDay;
            boolean combo = true;
            while (combo) {
                combo = false;
                for (Action action : results) {
                    if (action.getDateTime().getTime() < time
                            && action.getDateTime().getTime() > time - millisInDay) {
                        streak++;
                        time -= millisInDay;
                        combo = true;
                        break;
                    }
                }
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
        return streak;
    }

    /**
     * updates an Acion in the database.
     * @param action The action object which should be updated in database
     */
    public static void updateAction(Action action) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(action);
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
}
