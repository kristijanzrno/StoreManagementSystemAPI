/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.management.system;

import java.sql.*;

/**
 *
 * @author kristijanzrno
 */
public class DatabaseManipulator {

    Connection connection = null;
    Statement statement = null;
    ResultSet response = null;

    String user = "root";
    String pass = "password";
    

    public DatabaseManipulator() throws SQLException {
        try {
    Class.forName("com.mysql.jdbc.Driver");
} catch (ClassNotFoundException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
    
}
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_management_system?autoReconnect=true&useSSL=false", user, pass);
            statement = connection.createStatement();
            response = statement.executeQuery("select * from Users");
            while (response.next()) {
                System.out.println(response.getString("firstName") + ", " + response.getString("lastName"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

}
