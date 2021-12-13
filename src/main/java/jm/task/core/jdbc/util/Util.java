package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
   private static final String URL = "jdbc:mysql://localhost:3306/mysql";
   private static final  String USERNAME = "root";
   private static final String PASSWORD = "1418";

     public static Connection getNewConnection() throws SQLException, ClassNotFoundException {
//         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connection =  DriverManager.getConnection(URL,USERNAME,PASSWORD);
         return connection;
        }
}



