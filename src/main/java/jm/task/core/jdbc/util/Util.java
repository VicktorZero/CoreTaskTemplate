package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mysql";
    private static final  String USERNAME = "username";
    private static final String PASSWORD = "Password";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.URL,URL);
                settings.put(Environment.USER,USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception ex){
                System.out.println("not connection");
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }

     public static Connection getNewConnection() throws SQLException, ClassNotFoundException {

         Connection connection =  DriverManager.getConnection(URL,USERNAME,PASSWORD);
         return connection;
        }
}



