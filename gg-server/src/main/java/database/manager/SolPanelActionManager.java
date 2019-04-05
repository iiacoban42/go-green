package database.manager;

import database.entity.SolPanelAction;
import database.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;





public class SolPanelActionManager {
    /**
     * creates a continuous action and comitis it to database.
     * @param username a String representing the primarykey/username of user
     * @param scorePerDay an int representing the score gained per day.
     * @param numSolarPanels an int that can represent what is needed such as # of solar panels
     * @return a long that represents CAs primary key
     */
    public static long createSp(String username, int scorePerDay, int numSolarPanels) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        SolPanelAction solPanelAction = null;

        try {
            tx = session.beginTransaction();

            solPanelAction = new SolPanelAction(username, scorePerDay, numSolarPanels);
            session.save(solPanelAction);
            User user = UserManager.getUser(solPanelAction.getUser());
            user.addScore(scorePerDay);
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
        return solPanelAction.getId();
    }

    /**
     * deletes solar panel from database.
     * @param id a long representing primary key of continuouse action
     */
    public static void deleteSp(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SolPanelAction solPanelAction =
                    (SolPanelAction)session.get(SolPanelAction.class, id);
            session.delete(solPanelAction);
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
     * performs all updates required for "check in" including updating user score.
     * @param id a long representing primary key of continuouse action
     */
    public static void cashInSp(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SolPanelAction solPanelAction =
                    (SolPanelAction)session.get(SolPanelAction.class, id);
            solPanelAction.twentyFourHoursPassed();
            solPanelAction.cashIn();
            solPanelAction.setDateLastCashedIn(new Date());
            session.update(solPanelAction);
            String username = solPanelAction.getUser();
            User user = (User)session.get(User.class, username);
            user.addScore(solPanelAction.getScorePerDay());
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
     * gets SolPanelAction with primary key id.
     * @param id a long representing primary key of continuouse action
     * @return a SolPanelAction with primary key id
     */
    public static SolPanelAction getSp(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        SolPanelAction solPanelAction = null;

        try {
            tx = session.beginTransaction();
            solPanelAction = (SolPanelAction)session.get(SolPanelAction.class, id);
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
        return solPanelAction;
    }

    /**
     * lists all continuous actions in database.
     * @return a List of SolPanelAction in database
     */
    public static List<SolPanelAction> listSp() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<SolPanelAction> solPanelActions = null;

        try {
            tx = session.beginTransaction();
            solPanelActions = session.createQuery("From SolPanelAction").list();
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
        return solPanelActions;

    }

    /**
     * returns a list of all active continouse Actions of user.
     * @param username a String representing the primarykey/username of user
     * @return a List of Continuousection
     */
    public static SolPanelAction getActiveSpByUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        SolPanelAction solPanelAction = null;

        try {
            tx = session.beginTransaction();
            String hql = "FROM SolPanelAction WHERE user = :username AND dateEnded IS NULL";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            solPanelAction = (SolPanelAction) query.list().get(0);
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
        return solPanelAction;
    }

    /**
     * puts an end date on given SolPanelAction.
     * @param id a long representing primary key of continuouse action
     */
    public static void endSp(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SolPanelAction solPanelAction =
                    (SolPanelAction)session.get(SolPanelAction.class, id);
            solPanelAction.setDateEnded(new Date());
            session.update(solPanelAction);
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
     * Calculates amount of CO2 saved by producing energy by solar panels. The following source has been used for calculating the saved CO2 per solar panel:
     * https://www.gaslicht.com/energiebesparing/stroom-opwekken-met-zonnepanelen
     * @param username
     * @return amount of CO2 saved in grams
     */
    public Integer savedCO2(String username) {
        if (SolPanelActionManager.getActiveSpByUser(username) != null) {
            SolPanelAction solPanelAction = SolPanelActionManager.getActiveSpByUser(username);
            int amountSP = solPanelAction.getNumSolarPanels();
            int savedCO2perSP = 630 * 1000 / (6 * 365);
            int savedCO2 = 0;

            Date lastCashInDate = solPanelAction.getDateLastCashedIn();
            long lastCashInMillis = lastCashInDate.getTime();

            if (twentyFourHoursPassed(lastCashInMillis)) {
                savedCO2 = amountSP * savedCO2perSP;
                SolPanelActionManager.cashInSp(solPanelAction.getId());
            }

            return savedCO2;
        }
        return null;
    }

    public void newAmountSP (String username, int numSolarPanels) {
        int totalScore = 0;
        if (SolPanelActionManager.getActiveSpByUser(username) != null) {
            totalScore = SolPanelActionManager.getActiveSpByUser(username).getTotalScore();
            endSp(SolPanelActionManager.getActiveSpByUser(username).getId());
        }

        SolPanelAction solPanelAction = new SolPanelAction(username,0, numSolarPanels);
        int scorePerDay = savedCO2(username);
        solPanelAction.setScorePerDay(scorePerDay);
        solPanelAction.setTotalScore(totalScore);
    }


}
