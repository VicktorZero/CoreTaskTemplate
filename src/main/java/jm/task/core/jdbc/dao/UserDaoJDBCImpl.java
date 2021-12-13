package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
       try(Statement statement = Util.getNewConnection().createStatement()) {
           statement.execute("CREATE TABLE USERS " +
                   "(id INTEGER not NULL  AUTO_INCREMENT, " +
                   " Name VARCHAR(255), " +
                   " lastName VARCHAR(255), " +
                   " age INTEGER, " +
                   "PRIMARY KEY (id))");
           System.out.println("creat table in database");
       } catch (SQLException ex) {
           System.out.println("dont creat table");;
       } catch (ClassNotFoundException e){
           e.printStackTrace();
       }

    }

    public void dropUsersTable() {
        try(Statement statement = Util.getNewConnection().createStatement()){
            statement.executeUpdate("DROP TABLE USERS");
            System.out.println("table deleted in database");
        } catch (SQLException e) {
            System.out.println("dont deleted table");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = Util.getNewConnection().createStatement()){
            statement.executeUpdate("INSERT INTO USERS(name,lastname,age) VALUES('"+name+"','"+lastName+"','"+ age+"')");
        } catch (SQLException ex) {
            System.out.println("dont insert in table");
        } catch (ClassNotFoundException sd){
            sd.printStackTrace();
        }
    }

    public void removeUserById(long id) {
       try(Statement statement = Util.getNewConnection().createStatement()){
           statement.executeUpdate("DELETE FROM USERS WHERE ID = '"+id+"'");
       } catch (SQLException ex) {
           System.out.println("dont removeUserById");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
    }

    public List<User> getAllUsers() {
       List<User> result = new ArrayList<>();
       try(Statement statement = Util.getNewConnection().createStatement()){
           ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
           while (resultSet.next()){
               String name = resultSet.getString(2);
               String lastName = resultSet.getString(3);
               byte age = resultSet.getByte(4);

               User user = new User(name,lastName,age);
               result.add(user);
           }
       } catch (SQLException ex) {
           System.out.println("dont getAllUsers");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
        return result;
    }

    public void cleanUsersTable() {
      try (Statement statement = Util.getNewConnection().createStatement()){
          statement.executeUpdate("TRUNCATE TABLE USERS");
      } catch (SQLException ex) {
          System.out.println("dont cleanUsersTable");
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
    }
}
