/*import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class NewUser {

    public static void creatNewUser(String username, String hashPassword) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User(username, hashPassword);

        session.save(user);
        session.getTransaction().commit();

        session.close();

    }
}*/