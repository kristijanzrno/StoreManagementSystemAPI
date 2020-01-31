package store.management.system;

import java.sql.*;
import javax.ws.rs.QueryParam;
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
        return sendSQLQuery("SELECT * FROM Users WHERE userID=" + userID);
    }

    public String getAllUsers() {
        return sendSQLQuery("SELECT * FROM Users");
    }

    public String deleteUser(String userID) {
        return sendSQLUpdate("DELETE FROM Users WHERE userID=" + userID);
    }

    public String createUser(String username,
            String password,
            String firstName,
            String lastName,
            String email,
            String address,
            String phoneNumber,
            String accountType) {
        String query = "INSERT INTO Users (username, password, firstName, lastName, email, address, phoneNumber, accountType)"
                + " VALUES('"+username+"','"+password+"','"+firstName+"','"+lastName+"','"+email+"','"+address+"','"+phoneNumber+"',"+accountType+")";
        return sendSQLUpdate(query);
    }
       public String editUser(
            String userID,
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            String address,
            String phoneNumber,
            String accountType) {
        String query = "UPDATE Users SET username='"+username+"', password='"+password+"',firstName='"+firstName+"',"
                + "lastName='"+lastName+"',email='"+email+"',address='"+address+"',phoneNumber='"+phoneNumber+"',accountType="+accountType
                + " WHERE userID='"+userID+"'";
        System.out.println(query);
        return sendSQLUpdate(query);
    }

    private String sendSQLQuery(String query) {
        try {
            statement = connection.createStatement();
            ResultSet response = statement.executeQuery(query);
            if (response != null) {
                JSONArray result = resultToJSON(response);
                if (result != null) {
                    closeConnection();
                    return result.toString();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        closeConnection();
        return new JSONObject().toString();
    }

    private String sendSQLUpdate(String query) {
        JSONObject obj = new JSONObject();
        try {
            statement = connection.createStatement();
            int result = statement.executeUpdate(query);
            obj.put("result", result);
            closeConnection();
            return obj.toString();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        closeConnection();
        return obj.toString();
    }

    private void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    private JSONArray resultToJSON(ResultSet resultSet) {
        System.out.println(resultSet.toString());
        JSONArray jsonArray = new JSONArray();
        try {
            while (resultSet.next()) {
                int numberOfRows = resultSet.getMetaData().getColumnCount();
                JSONObject object = new JSONObject();
                for (int i = 0; i < numberOfRows; i++) {
                    object.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(),
                            resultSet.getObject(i + 1));
                }
                jsonArray.put(object);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return jsonArray;
    }

}
