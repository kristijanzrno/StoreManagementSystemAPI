/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.management.system;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author kristijanzrno
 */
public class DatabaseManipulator {

    private Connection connection = null;
    private Statement statement = null;
    private String user = "root";
    private String pass = "password";

    public DatabaseManipulator() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace(); 
        }
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_management_system?autoReconnect=true&useSSL=false", user, pass);

    }

    public String getUser(String userID) {
        // todo, this is just an example how we are gonna do it
        // get the resultset, extract it as JSON
        String query = "SELECT * FROM Users WHERE userID=" + userID;
        ResultSet response = sendSQLQuery(query);
        if(response != null){
            JSONArray result = resultToJSON(response);
            if(result!=null)
                return result.toString();
        }
        return "";
    }
    
    private ResultSet sendSQLQuery(String query){
        try {
            Statement statement = connection.createStatement(); 
           return statement.executeQuery(query);
        } catch (SQLException ex) {
              return null;
        }
    }
    
    private void closeConnection(){
        try {
            if(statement!=null)
                statement.close();
            if(connection!=null)
                connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManipulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private JSONArray resultToJSON(ResultSet resultSet){
        JSONArray jsonArray = new JSONArray();
        try {
            while(resultSet.next()){
                int numberOfRows = resultSet.getMetaData().getColumnCount();
                for(int i = 0; i<numberOfRows; i++){
                    JSONObject object = new JSONObject();
                    object.put(resultSet.getMetaData().getColumnLabel(i+1).toLowerCase(),
                            resultSet.getObject(i+1));
                    jsonArray.put(object);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("json error...");
        }
        closeConnection();
        return jsonArray;
    } 

}
