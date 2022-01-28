package peaksoft.dao;

import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable()  {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("Create table successfully!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Drop table successfully!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            session.close();
            System.out.println("Save User  successfully!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            session.close();
            System.out.println("Remove ID successfully!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();

            List<User> list = session.createQuery("from User ").getResultList();

            session.getTransaction().commit();
            session.close();
            System.out.println("Get All User successfully!\n" + list.size());
            return list;

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE users").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Clean User successfully!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}
