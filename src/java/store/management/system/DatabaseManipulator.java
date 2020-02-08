package store.management.system;

import Data.CustomerPurchaseItem;
import Data.PurchaseInvoice;
import Data.PurchaseItem;
import Data.RefillInvoice;
import Data.StockItem;
import Data.Supplier;
import Data.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    Gson gson;

    public DatabaseManipulator() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_management_system?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", user, pass);
        gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
    }
    
    // USER FUNCTIONS
    
    public Connection ConnectDB(){
        return connection;
    }

    public String getUser(String userID) {
        return sendSQLQuery("SELECT * FROM Users WHERE userID=" + userID, false);
    }
    
    public String getUserWithUsername(String username) {
        return sendSQLQuery("SELECT * FROM Users WHERE username='" + username+"'", false);
    }

    public String getAllUsers() {
        return sendSQLQuery("SELECT * FROM Users", true);
    }

    public String deleteUser(String userID) {
        return sendSQLUpdate("DELETE FROM Users WHERE userID=" + userID);
    }

    public String createUser(String json) {
        User user = gson.fromJson(json, User.class);
        String query = "INSERT INTO Users (username, password, firstName, lastName, email, address, phoneNumber, accountType)"
                + " VALUES('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getEmail() + "','" + user.getAddress() + "','" + user.getPhoneNumber() + "'," + user.getAccountType() + ")";
        return sendSQLUpdate(query);
    }

    public String editUser(String userID, String json) {
        User user = gson.fromJson(json, User.class);
        String query = "UPDATE Users SET username='" + user.getUsername() + "', password='" + user.getPassword() + "',firstName='" + user.getFirstName() + "',"
                + "lastName='" + user.getLastName() + "',email='" + user.getEmail() + "',address='" + user.getAddress() + "',phoneNumber='" + user.getPhoneNumber() + "',accountType=" + user.getAccountType()
                + " WHERE userID='" + userID + "'";
        return sendSQLUpdate(query);
    }

    // SUPPLIER FUNCTIONS
    
    public String getSupplier(String supplierID) {
        return sendSQLQuery("SELECT * FROM suppliers WHERE supplierID=" + supplierID, false);
    }

    public String getAllSuppliers() {
        return sendSQLQuery("SELECT * FROM suppliers", true);
    }

    public String deleteSupplier(String supplierID) {
        return sendSQLUpdate("DELETE FROM suppliers WHERE supplierID=" + supplierID);
    }

    public String createSupplier(String json) {
        Supplier supplier = gson.fromJson(json, Supplier.class);
        String query = "INSERT INTO suppliers (name, description, address, shipmentAddress, email, phoneNumber)"
                + " VALUES('" + supplier.getName() + "','" + supplier.getDescription() + "','" + supplier.getAddress() + "','" + supplier.getShipmentAddress() + "','" + supplier.getEmail() + "','" + supplier.getPhoneNumber() + "')";
        return sendSQLUpdate(query);
    }

    public String editSupplier(String supplierID, String json) {
        Supplier supplier = gson.fromJson(json, Supplier.class);
        String query = "UPDATE suppliers SET name='" + supplier.getName() + "', description='" + supplier.getDescription() + "', address='" + supplier.getAddress() + "',"
                + "shipmentAddress='" + supplier.getShipmentAddress() + "',email='" + supplier.getEmail() + "',phoneNumber='" + supplier.getPhoneNumber()
                + "' WHERE supplierID='" + supplierID + "'";
        return sendSQLUpdate(query);
    }

    // STOCK ITEM FUNCTIONS
    
    public String getItem(String itemID) {
        return sendSQLQuery("SELECT * FROM stockitems WHERE itemID=" + itemID, false);
    }

    public String getAllItems() {
        return sendSQLQuery("SELECT * FROM stockitems", true);
    }

    public String deleteItem(String itemID) {
        return sendSQLUpdate("DELETE FROM stockitems WHERE itemID=" + itemID);
    }

    public String createItem(String json) {
        StockItem item = gson.fromJson(json, StockItem.class);
        String query = "INSERT INTO stockitems (name, description, quantity, type, manufacturerPartNum, cost, salesPrice, preferredSupplier, VAT, dateCreated, lastModified, isRentable)"
                + " VALUES('" + item.getName() + "','" + item.getDescription() + "','" + item.getQuantity() + "','" + item.getType() + "','" + item.getManufacturerPartNum() + "','" + item.getCost() + "','" + item.getSalesPrice() + "'," + item.getPreferredSupplier() + ",'" + item.getVAT() + "','" + item.getDateCreated() + "','" + item.getLastModified() + "','" + (""+item.isRentable()).toUpperCase() + "')";
        return sendSQLUpdate(query);
    }

    public String editItem(String itemID, String json) {
        StockItem item = gson.fromJson(json, StockItem.class);
        String query = "UPDATE stockitems SET name='" + item.getName() + "', description='" + item.getDescription() + "',quantity='" + item.getQuantity() + "',"
                + "type='" + item.getType() + "',manufacturerPartNum='" + item.getManufacturerPartNum() + "',cost='" + item.getCost() + "',salesPrice='" + item.getSalesPrice() + "',"
                + "preferredSupplier='" + item.getPreferredSupplier() + "', VAT='" + item.getVAT() + "',dateCreated='" + item.getDateCreated() + "',lastModified='" + item.getLastModified() + "',isRentable='" + (""+item.isRentable()).toUpperCase()
                + "' WHERE itemID='" + itemID + "'";
        return sendSQLUpdate(query);
    }

    public String updateStock(String itemID, String noOfItems) {
        String query = "UPDATE StockItems SET quantity=quantity+" + noOfItems
                + " WHERE itemID='" + itemID + "'";
        return sendSQLUpdate(query);
    }

    // PURCHASE INVOICE FUNCTIONS 
    
    public String getPurchaseInvoice(String invoiceID) {
        String response = sendSQLQuery("SELECT * FROM PurchaseInvoices WHERE invoiceID="+invoiceID+";", false);
        System.out.println(response);
        PurchaseInvoice invoice = gson.fromJson(response, PurchaseInvoice.class);
        response = sendSQLQuery("SELECT * FROM PurchaseItems WHERE invoiceID="+invoiceID+";", true);
        CustomerPurchaseItem[] items = gson.fromJson(response, CustomerPurchaseItem[].class);
        invoice.getItems().addAll(Arrays.asList(items));
        return gson.toJson(invoice);
    }

    public String getAllPurchaseInvoices() {
        String response = sendSQLQuery("SELECT * FROM PurchaseInvoices;", true);
        PurchaseInvoice[] invoices = gson.fromJson(response, PurchaseInvoice[].class);
        for(PurchaseInvoice invoice : invoices){
            response = sendSQLQuery("SELECT * FROM PurchaseItems WHERE invoiceID="+invoice.getInvoiceID()+";", true);
            CustomerPurchaseItem[] items = gson.fromJson(response, CustomerPurchaseItem[].class);
            invoice.getItems().addAll(Arrays.asList(items));
        }
        return gson.toJson(invoices);
    }

    public String deletePurchaseInvoice(String invoiceID) {
        String query = "DELETE * FROM PurchaseItems WHERE invoiceID=" + invoiceID + "; "
                + "DELETE FROM PurchaseInvoices WHERE invoiceID=" + invoiceID + ";";
        return sendSQLUpdate(query);
    }

    public String createPurchaseInvoice(String json) {
        PurchaseInvoice invoice = gson.fromJson(json, PurchaseInvoice.class);
        ArrayList<StockItem> stockItems = new ArrayList<>();
        for(CustomerPurchaseItem cpItem : invoice.getItems()){
            stockItems.add(gson.fromJson(getItem(cpItem.getItemID()), StockItem.class));
        }
        invoice.calculateTotalPrice(stockItems);
        String invoiceQuery = "INSERT INTO PurchaseInvoices (userID, invoiceDate, invoiceDescription, totalPrice, hasRentedItems)" + " VALUES('" + invoice.getUserID() + "','" + invoice.getInvoiceDate() + "','" + invoice.getInvoiceDescription() + "','" + invoice.getTotalPrice() + "'," + (""+invoice.getHasRentedItems()).toUpperCase() + ");";
        String response = sendSQLUpdate(invoiceQuery);
        String id = sendSQLQuery("SELECT LAST_INSERT_ID();", false).replace("{\"last_insert_id()\":", "").replace("}", "");
        for (CustomerPurchaseItem item : invoice.getItems()) {
            String query = "INSERT INTO PurchaseItems (invoiceID, itemID, quantity, dateRented, dateReturned) VALUES(" + id + ", " + item.getItemID() + ", " + item.getQuantity();
            if(item.getDateRented() != null){
                query += ", '"+item.getDateRented()+"'";
                if(item.getDateReturned() != null)
                    query += ", '"+item.getDateReturned()+"'";
                else
                    query+=",null";
            }else{
                query +=",null,null";
            }
            query += ");";
            sendSQLUpdate(query);
        }
        return response;
    }

    public String editPurchaseInvoice(String invoiceID, String json) {
        PurchaseInvoice invoice = gson.fromJson(json, PurchaseInvoice.class);
        String invoiceQuery = "UPDATE PurchaseInvoices SET userID="+invoice.getUserID()+",invoiceDate='"+invoice.getInvoiceDate()+"', invoiceDescription='"+invoice.getInvoiceDescription()+"', totalPrice="+invoice.getTotalPrice() + ", hasRentedItems="+(""+invoice.getHasRentedItems()).toUpperCase()
                + " WHERE invoiceID="+invoiceID + ";";
        System.out.println(invoiceQuery);
        String response = sendSQLUpdate(invoiceQuery);
        for (CustomerPurchaseItem item : invoice.getItems()) {
            String query = "UPDATE PurchaseItems SET invoiceID="+ invoice.getInvoiceID() +", itemID="+item.getItemID() + ", quantity="+item.getQuantity();
                    if(item.getDateRented() != null)
                        query += ", dateRented='"+item.getDateRented()+"'";
                    if(item.getDateReturned() != null)
                        query += ", dateReturned='"+item.getDateReturned()+"'";
                    query += " WHERE invoiceID="+invoiceID+";";
            System.out.println(query);
            sendSQLUpdate(query);
        }
        return response;
    }
    
    // REFILL INVOICES FUNCTIONS
    
    
    public String getRefillInvoice(String invoiceID) {
        String response = sendSQLQuery("SELECT * FROM RefillInvoices WHERE invoiceID="+invoiceID+";", false);
        System.out.println(response);
        RefillInvoice invoice = gson.fromJson(response, RefillInvoice.class);
        response = sendSQLQuery("SELECT * FROM RefillItems WHERE invoiceID="+invoiceID+";", true);
        PurchaseItem[] items = gson.fromJson(response, PurchaseItem[].class);
        invoice.getItems().addAll(Arrays.asList(items));
        return gson.toJson(invoice);
    }

    public String getAllRefillInvoices() {
        String response = sendSQLQuery("SELECT * FROM RefillInvoices;", true);
        RefillInvoice[] invoices = gson.fromJson(response, RefillInvoice[].class);
        for(RefillInvoice invoice : invoices){
            response = sendSQLQuery("SELECT * FROM RefillItems WHERE invoiceID="+invoice.getInvoiceID()+";", true);
            PurchaseItem[] items = gson.fromJson(response, PurchaseItem[].class);
            invoice.getItems().addAll(Arrays.asList(items));
        }
        return gson.toJson(invoices);
    }

    public String deleteRefillInvoice(String invoiceID) {
          String query = "DELETE * FROM RefillItems WHERE invoiceID=" + invoiceID + "; "
                + "DELETE FROM RefillInvoices WHERE invoiceID=" + invoiceID + ";";
        return sendSQLUpdate(query);
    }

    public String createRefillInvoice(String json) {
        System.out.println(json);
        RefillInvoice invoice = gson.fromJson(json, RefillInvoice.class);
        String invoiceQuery = "INSERT INTO RefillInvoice (userID, invoiceDate, invoiceDescription, totalPrice, supplierID, shippmentAddress)" + " VALUES('" + invoice.getUserID() + "','" + invoice.getInvoiceDate() + "','" + invoice.getInvoiceDescription() + "','" + invoice.getTotalPrice() + "', '" + invoice.getSupplierID() + "', '"+invoice.getShipmentAddress()+"')";
        System.out.println(invoiceQuery);
        String response = sendSQLUpdate(invoiceQuery);
        String id = sendSQLQuery("SELECT LAST_INSERT_ID();", false).replace("{\"last_insert_id()\":", "").replace("}", "");
        for (PurchaseItem item : invoice.getItems()) {
            String query = "INSERT INTO Refillitems (invoiceID, itemID, quantity) VALUES(" + id + ", " + item.getItemID() + ", " + item.getQuantity() + ");";
            System.out.println(query);
            sendSQLUpdate(query);
        }
        return response;
    }

    public String editRefillInvoice(String invoiceID, String json) {
        RefillInvoice invoice = gson.fromJson(json, RefillInvoice.class);
        String invoiceQuery = "UPDATE RefillInvoice SET userID="+invoice.getUserID()+",invoiceDate='"+invoice.getInvoiceDate()+"', invoiceDescription='"+invoice.getInvoiceDescription()+"', totalPrice="+invoice.getTotalPrice() + ", supplierID=" + invoice.getSupplierID() + ", shipmentAddress='"+invoice.getShipmentAddress()+"'"
                + " WHERE invoiceID="+invoiceID + ";";
        System.out.println(invoiceQuery);
        String response = sendSQLUpdate(invoiceQuery);
        for (PurchaseItem item : invoice.getItems()) {
            String query = "UPDATE Refillitems SET invoiceID="+ invoice.getInvoiceID() +", itemID="+item.getItemID() + ", quantity="+item.getQuantity()
                    + " WHERE invoiceID="+invoiceID+";";
            System.out.println(query);
            sendSQLUpdate(query);
        }
        return response;
    }
    // Model functions
    public String calculatePurchaseInvoiceTotalPrice(String invoiceID, String userID, String json){
        String invoicID = invoiceID;
        String usrID = userID;
        String calculateTotalPrice = "SELECT * FROM refillinvoices WHERE invoiceID = " + invoicID + "AND userID = " + usrID + ";";
        String response = sendSQLQuery(calculateTotalPrice, false);
        return response;
    }
    
    public String calculatePurchaseInvoiceTotalPrice(String invoiceID) {
        //String response = sendSQLQuery("SELECT totalPrice FROM PurchaseInvoices WHERE invoiceID="+invoiceID+";", false);
        String response = sendSQLQuery("SELECT * FROM PurchaseInvoices;", true);
        PurchaseInvoice[] invoices = gson.fromJson(response, PurchaseInvoice[].class);
        for(PurchaseInvoice invoice : invoices){
            response = sendSQLQuery("SELECT itemid, quantity FROM PurchaseItems WHERE invoiceID="+invoice.getInvoiceID()+";", true);
            CustomerPurchaseItem[] items = gson.fromJson(response, CustomerPurchaseItem[].class);
            invoice.getItems().addAll(Arrays.asList(items));
        }
        String returnedQuery = gson.toJson(invoices);
        System.out.println(returnedQuery.split("itemid")[1].split("\"")[2]); // item id
        System.out.println(returnedQuery.split("quantity")[1].split(":")[1].split("}")[0]); // item quantity
        return gson.toJson(invoices);
    }
    

    
    
    // PRIVATE DATABASE MANIPULATION FUNCTIONS

    private String sendSQLQuery(String query, boolean asArray) {
        try {
            statement = connection.createStatement();
            ResultSet response = statement.executeQuery(query);
            if (response != null) {
                JSONArray result = resultToJSON(response);
                if (result != null) {
                    //closeConnection();
                    if (result.length() == 1 && !asArray) {
                        return result.get(0).toString();
                    }
                    return result.toString();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        //closeConnection();
        return new JSONObject().toString();
    }

    private String sendSQLUpdate(String query) {
        JSONObject obj = new JSONObject();
        try {
            statement = connection.createStatement();
            int result = statement.executeUpdate(query);
            obj.put("result", result);
            //closeConnection();
            return obj.toString();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        //closeConnection();
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
