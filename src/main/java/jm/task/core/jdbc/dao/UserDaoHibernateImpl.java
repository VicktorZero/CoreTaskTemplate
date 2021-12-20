package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            String sqlCommand = "CREATE TABLE USERS(Id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(255),lastName VARCHAR(255) ,age INTEGER)";
            Query query = session.createSQLQuery(sqlCommand).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("creat table in database");
        } catch (Exception ex){
            if(transaction != null){
                transaction.rollback();
                System.out.println("dont creat table");
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE USERS").addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("table deleted in database");
        } catch (Exception ex){
            if(transaction != null){
                transaction.rollback();
                System.out.println("dont deleted table");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User(name,lastName,age);
            Long userId = (Long) session.save(user);
            session.getTransaction().commit();

        } catch (Exception ex){
            if(transaction != null){
                transaction.rollback();
                System.out.println("dont save user");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            User user = (User) session.get(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception ex){
            if(transaction != null){
                transaction.rollback();
                System.out.println("dont removeUserByID");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> result = new ArrayList<>();
        try{
            transaction = session.beginTransaction();
            result =  session.createSQLQuery("SELECT * FROM USERS").addEntity(User.class).list();
            session.getTransaction().commit();
            System.out.println("getAllUsers");
        } catch (Exception ex){
            if(transaction != null){
                transaction.rollback();
                System.out.println("dont getAllUsers");
            }
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE USERS");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex){
            if(transaction != null){
                transaction.rollback();
                System.out.println("dont cleanUsersTable");
            }
        } finally {
            session.close();
        }

    }
}
