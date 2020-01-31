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
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_management_system?autoReconnect=true&useSSL=false&serverTimezone=UTC", user, pass);
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
    // supplier functions
       
    public String getSupplier(String supplierID) {
        return sendSQLQuery("SELECT * FROM suppliers WHERE supplierID=" + supplierID);
    }
    
    public String getAllSuppliers() {
        return sendSQLQuery("SELECT * FROM suppliers");
    }
    
    public String deleteSupplier(String supplierID) {
        return sendSQLUpdate("DELETE FROM suppliers WHERE supplierID=" + supplierID);
    }
    
    public String createSupplier(String supplierName,
            String supplierShippmentAddress,
            String supplierAddress,
            String supplierEmail,
            String supplierPhoneNumber,
            String supplierDescription) {
        String query = "INSERT INTO suppliers (supplierName, supplierShippmentAddress, supplierAddress, supplierEmail, supplierPhoneNumber, supplierDescription)"
                + " VALUES('"+supplierName+"','"+supplierShippmentAddress+"','"+supplierAddress+"','"+supplierEmail+"','"+supplierPhoneNumber+"','"+supplierDescription+")";
        return sendSQLUpdate(query);
    }
    public String editSupplier(String supplierID,
            String supplierName,
            String supplierShippmentAddress,
            String supplierAddress,
            String supplierEmail,
            String supplierPhoneNumber,
            String supplierDescription) {
        String query = "UPDATE suppliers SET supplierName='"+supplierName+"', supplierShippmentAddress='"+supplierShippmentAddress+"',supplierAddress='"+supplierAddress+"',"
                + "supplierEmail='"+supplierEmail+"',supplierPhoneNumber='"+supplierPhoneNumber+"',supplierDescription='"+supplierDescription
                + " WHERE supplierID='"+supplierID+"'";
        System.out.println(query);
        return sendSQLUpdate(query);
    }
    
    // stock item functions
    public String getItem(String itemID) {
        return sendSQLQuery("SELECT * FROM stockitems WHEREitemID=" + itemID);
    }
    
    public String getAllItems() {
        return sendSQLQuery("SELECT * FROM stockitems");
    }
    
    public String deleteItem(String itemID) {
        return sendSQLUpdate("DELETE FROM stockitems WHERE itemID=" + itemID);
    }
    
    public String createItem(String itemDescription,
            String itemQuantity,
            String itemType,
            String itemManufacturerPartNum,
            String itemPurchaseInfo,
            String itemDescriptionOfSale,
            String itemCost,
            String itemSalesPrice,
            String itemPrefferedSupplier,
            String itemVAT,
            String itemLastModifiedData) {
        String query = "INSERT INTO stockitems (itemDescription, itemQuantity, itemType, itemManufacturerPartNum, itemPurchaseInfo, itemDescriptionOfSale, itemCost, itemSalesPrice, itemPrefferedSupplier, itemVAT, itemLastModifiedData)"
                + " VALUES('"+itemDescription+"','"+itemQuantity+"','"+itemType+"','"+itemManufacturerPartNum+"','"+itemPurchaseInfo+"','"+itemDescriptionOfSale+"','"+itemCost+"',"+itemSalesPrice+",'"+itemPrefferedSupplier+"','"+itemVAT+"','"+itemLastModifiedData+")";
        return sendSQLUpdate(query);
    }
       public String editItem(String itemID,
            String itemDescription,
            String itemQuantity,
            String itemType,
            String itemManufacturerPartNum,
            String itemPurchaseInfo,
            String itemDescriptionOfSale,
            String itemCost,
            String itemSalesPrice,
            String itemPrefferedSupplier,
            String itemVAT,
            String itemLastModifiedData) {
        String query = "UPDATE stockitems SET itemDescription='"+itemDescription+"', itemQuantity='"+itemQuantity+"',itemType='"+itemType+"',"
                + "itemManufacturerPartNum='"+itemManufacturerPartNum+"',itemPurchaseInfo='"+itemPurchaseInfo+"',itemDescriptionOfSale='"+itemDescriptionOfSale+"',itemCost='"+itemCost+ "',"
                + "itemSalesPrice="+itemSalesPrice+"itemPrefferedSupplier='"+itemPrefferedSupplier+"',itemVAT='"+itemVAT+"',itemLastModifiedData='"+itemLastModifiedData
                + " WHERE itemID='"+itemID+"'";
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
