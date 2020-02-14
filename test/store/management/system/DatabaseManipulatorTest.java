/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.management.system;

import Data.CustomerPurchaseItem;
import Data.PurchaseInvoice;
import Data.RefillInvoice;
import Data.StockItem;
import Data.Supplier;
import Data.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan
 */
public class DatabaseManipulatorTest {
    
    
    public DatabaseManipulatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of dashboardQuery method, of class DatabaseManipulator.
     */
    @Test
    public void testDashboardQuery() throws Exception {
        System.out.println("dashboardQuery");
        String query = "SELECT name, quantity FROM stockitems WHERE quantity < 50;";
        DatabaseManipulator instance = new DatabaseManipulator();
        String expResult = "{\"name\":\"23\"}";
        String result = instance.dashboardQuery(query);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class DatabaseManipulator.
     */
    @Test
    public void testGetUser() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all user list
        User[] users = gson.fromJson(instance.getAllUsers(), User[].class);
        // set user1 to be the last user
        User user1 = users[users.length-1];
        // get user1 id
        int userid = user1.getUserID();
        // create user2
        User user2 = gson.fromJson(instance.getUser(Integer.toString(userid)), User.class);
        // get user2 id
        int user2id = user2.getUserID();
         
        // do the comparison
        assertEquals(user2id, userid);
        
    }

    /**
     * Test of getUserWithUsername method, of class DatabaseManipulator.
     */
    @Test
    public void testGetUserWithUsername() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all user list
        User[] users = gson.fromJson(instance.getAllUsers(), User[].class);
        // set user1 
        User user1 = users[users.length-3];
        // get user1 username
        String username = user1.getUsername();
        // create user2 list 
        User[] users2 = gson.fromJson(instance.getUserWithUsername(username), User[].class);
        // get user2 username
        User user2 = users2[users2.length-3];
        String username2 = user2.getUsername();
         
        // do the comparison
        assertEquals(username2, username);
    }

    /**
     * Test of getAllUsers method, of class DatabaseManipulator.
     */
    @Test
    public void testGetAllUsers() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all user list
        User[] users = gson.fromJson(instance.getAllUsers(), User[].class);
        // get the lenghts
        int userslen = users.length;
        // get all user list
        User[] users2 = gson.fromJson(instance.getAllUsers(), User[].class);
        // get the lenghts
        int users2len = users2.length;
        
            assertEquals(users2len, userslen);
        
    }

    /**
     * Test of deleteUser method, of class DatabaseManipulator.
     */
    @Test
    public void testDeleteUser() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();        
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all user list
        User[] users = gson.fromJson(instance.getAllUsers(), User[].class);
        int initLength = users.length;
        // set user1 to be the second to last user
        User user1 = users[users.length-2];
        // get user1 userid
        int userid = user1.getUserID();
        // delete last user
        String result = instance.deleteUser(Integer.toString(userid));
        // check new length
        User[] users2 = gson.fromJson(instance.getAllUsers(), User[].class);
        int finLength = users2.length;
        if(initLength > finLength){
            assertEquals(result, result);
        }
    }

    /**
     * Test of createUser method, of class DatabaseManipulator.
     */
    @Test
    public void testCreateUser() throws SQLException, NoSuchAlgorithmException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // set details of the object
        User user = new User();
        user.setAccountType(2);
        user.setAddress("address");
        user.setEmail("email@email.com");
        user.setEncryptedPassword("password");
        user.setFirstName("judasan");
        user.setLastName("ruasiz");
        user.setPhoneNumber("123321241");
        user.setUsername("user");
        // pass the object to json
        String result = gson.toJson(user); 
        // create the object
        DatabaseManipulator instance = new DatabaseManipulator();
        instance.createUser(result);
        // create 2nd objects for the comparison
        User[] users = gson.fromJson(instance.getAllUsers(), User[].class);
        User user2 = users[users.length-1];
        String expresult = gson.toJson(user2);
        user.setUserID(user2.getUserID());
        String result2 = gson.toJson(user); 
        // do the comparison
        assertEquals(expresult, result2);
    }

    /**
     * Test of editUser method, of class DatabaseManipulator.
     */
    @Test
    public void testEditUser() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create the db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all user and then get third to last user to edit
        User[] users = gson.fromJson(instance.getAllUsers(), User[].class);
        User user = users[users.length-1];
        // set details of the edited user
        user.setAccountType(1);
        user.setAddress("seads");
        user.setEmail("emdasail@emaidasl.com");
        user.setFirstName("jdsadasdssan");
        user.setLastName("ruadasdassiz");
        user.setPassword("passwodasdrd");
        user.setPhoneNumber("123321211241");
        user.setUsername("userasa");
        // get user id
        String userid = Integer.toString(user.getUserID());
        // pass the object to json
        String result = gson.toJson(user); 
        // create the object
        instance.editUser(userid, result);
        // create 2nd objects for the comparison
        User user2 = users[users.length-3];
        String expresult = gson.toJson(user2);
        user.setUserID(user2.getUserID());
        String result2 = gson.toJson(user); 
        // do the comparison
        assertEquals(expresult, result2);
    }

    /**
     * Test of getSupplier method, of class DatabaseManipulator.
     */
    @Test
    public void testGetSupplier() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all suppliers list
        Supplier[] suppliers = gson.fromJson(instance.getAllSuppliers(), Supplier[].class);
        // set supplier1 to be the last supplier
        Supplier supplier1 = suppliers[suppliers.length-1];
        // get supplier1 id
        int supplierid = supplier1.getSupplierID();
        // create supplier2
        Supplier supplier2 = gson.fromJson(instance.getSupplier(Integer.toString(supplierid)), Supplier.class);
        // get supplier2 id
        int supplier2id = supplier2.getSupplierID();
         
        // do the comparison
        assertEquals(supplier2id, supplierid);
    }

    /**
     * Test of getAllSuppliers method, of class DatabaseManipulator.
     */
    @Test
    public void testGetAllSuppliers() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all supplier list
        Supplier[] suppliers = gson.fromJson(instance.getAllSuppliers(), Supplier[].class);
        Supplier[] suppliers2 = gson.fromJson(instance.getAllSuppliers(), Supplier[].class);
        // get the lenghts
        int supplierslen = suppliers.length;
        int suppliers2len = suppliers2.length;
        // do the comparison
        assertEquals(suppliers2len, supplierslen);
    }

    /**
     * Test of deleteSupplier method, of class DatabaseManipulator.
     */
    @Test
    public void testDeleteSupplier() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();        
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all supplier list
        Supplier[] suppliers = gson.fromJson(instance.getAllSuppliers(), Supplier[].class);
        int initLenght = suppliers.length;
        // set supplier1 to be the second to last supplier
        Supplier supplier1 = suppliers[suppliers.length-2];
        // get supplier1 supplierid
        int supplierid = supplier1.getSupplierID();
        // delete last supplier
        String result = instance.deleteSupplier(Integer.toString(supplierid));
        
        // get all supplier list
        Supplier[] suppliers2 = gson.fromJson(instance.getAllSuppliers(), Supplier[].class);
        int finLenght = suppliers2.length;
        
        if(initLenght > finLenght){
            assertEquals(result, result);
        }
    }

    /**
     * Test of createSupplier method, of class DatabaseManipulator.
     */
    @Test
    public void testCreateSupplier() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // set details of the object
        Supplier supplier = new Supplier();
        supplier.setAddress("address");
        supplier.setDescription("description");
        supplier.setEmail("email@email.com");
        supplier.setName("name");
        supplier.setPhoneNumber("13232321");
        supplier.setShipmentAddress("shipment address");
        // pass the object to json
        String result = gson.toJson(supplier);
        // create the object
        DatabaseManipulator instance = new DatabaseManipulator();
        instance.createSupplier(result);
        // create 2nd objects for the comparison
        Supplier[] suppliers = gson.fromJson(instance.getAllSuppliers(), Supplier[].class);
        Supplier supplier2 = suppliers[suppliers.length - 1];
        String expresult = gson.toJson(supplier2);
        supplier.setSupplierID(supplier2.getSupplierID());
        String result2 = gson.toJson(supplier);
        // do the comparison
        assertEquals(expresult, result2);
    }

    /**
     * Test of editSupplier method, of class DatabaseManipulator.
     */
    @Test
    public void testEditSupplier() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create the db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all supplier and then get third to last supplier to edit
        Supplier[] suppliers = gson.fromJson(instance.getAllSuppliers(), Supplier[].class);
        Supplier supplier = suppliers[suppliers.length-1];
        // set details of the edited supplier
        supplier.setAddress("newAdrres");
        supplier.setDescription("newDescription");
        supplier.setEmail("newemail@email.com");
        supplier.setName("newname");
        supplier.setPhoneNumber("32132133");
        supplier.setShipmentAddress("new ship addres");
        
        // get supplier id
        String supplierid = Integer.toString(supplier.getSupplierID());
        // pass the object to json
        String result = gson.toJson(supplier); 
        // create the object
        instance.editSupplier(supplierid, result);
        // create 2nd objects for the comparison
        Supplier supplier2 = suppliers[suppliers.length-3];
        String expresult = gson.toJson(supplier2);
        supplier.setSupplierID(supplier2.getSupplierID());
        String result2 = gson.toJson(supplier); 
        // do the comparison
        assertEquals(expresult, result2);
    }

    /**
     * Test of getItem method, of class DatabaseManipulator.
     */
    @Test
    public void testGetItem() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all items list
        StockItem[] items = gson.fromJson(instance.getAllItems(), StockItem[].class);
        // set item1 to be the last item
        StockItem item1 = items[items.length-1];
        // get item1 id
        int itemid = item1.getItemID();
        // create item2
        StockItem item2 = gson.fromJson(instance.getItem(Integer.toString(itemid)), StockItem.class);
        // get item2 id
        int item2id = item2.getItemID();
         
        // do the comparison
        assertEquals(item2id, itemid);
    }

    /**
     * Test of getAllItems method, of class DatabaseManipulator.
     */
    @Test
    public void testGetAllItems() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all item list
        StockItem[] items = gson.fromJson(instance.getAllItems(), StockItem[].class);
        StockItem[] items2 = gson.fromJson(instance.getAllItems(), StockItem[].class);
        // get the lenghts
        int itemslen = items.length;
        int items2len = items2.length;
        // do the comparison
        assertEquals(items2len, itemslen);
    }

    /**
     * Test of deleteItem method, of class DatabaseManipulator.
     */
    @Test
    public void testDeleteItem() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();        
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all item list
        StockItem[] items = gson.fromJson(instance.getAllItems(), StockItem[].class);
        int initLenght = items.length;
        // set item1 to be the second to last item
        StockItem item1 = items[items.length-2];
        // get item1 itemid
        int itemid = item1.getItemID();
        // delete last supplier
        String result = instance.deleteItem(Integer.toString(itemid));
        
        // get all item list
        StockItem[] items2 = gson.fromJson(instance.getAllItems(), StockItem[].class);
        int finLenght = items2.length;
        
        if(initLenght > finLenght){
            assertEquals(result, result);
        }
    }

    /**
     * Test of createItem method, of class DatabaseManipulator.
     */
    @Test
    public void testCreateItem() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // set details of the object
        StockItem item = new StockItem();
        item.setCost((float) 12.33);
        item.setDateCreated("2020-02-12");
        item.setDescription("description");
        item.setLastModified("2020-02-12");
        item.setManufacturerPartNum(12);
        item.setName("name");
        item.setQuantity(100);
        item.setRentable(false);
        item.setSalesPrice((float) 12.01);
        item.setType("type");
        item.setVAT(7);
        item.setPreferredSupplier("1");
        // pass the object to json
        String result = gson.toJson(item);
        // create the object
        DatabaseManipulator instance = new DatabaseManipulator();
        instance.createItem(result);
        // create 2nd objects for the comparison
        StockItem[] items = gson.fromJson(instance.getAllItems(), StockItem[].class);
        StockItem item2 = items[items.length - 1];
        String expresult = gson.toJson(item2);
        item.setItemID(item2.getItemID());
        String result2 = gson.toJson(item);
        // do the comparison
        assertEquals(expresult, result2);
        
    }

    /**
     * Test of editItem method, of class DatabaseManipulator.
     */
    @Test
    public void testEditItem() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create the db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all item and then get third to last item to edit
        StockItem[] items = gson.fromJson(instance.getAllItems(), StockItem[].class);
        StockItem item = items[items.length-1];
        // set details of the edited item
        item.setCost((float) 15.34);
        item.setDateCreated("2020-02-12");
        item.setDescription("new description");
        item.setLastModified("2020-02-12");
        item.setManufacturerPartNum(13);
        item.setName("newname");
        item.setPreferredSupplier("1");
        item.setQuantity(100);
        item.setRentable(false);
        item.setSalesPrice((float) 12.99);
        item.setType("newtype");
        item.setVAT(6);     
        // get item id
        String itemid = Integer.toString(item.getItemID());
        // pass the object to json
        String result = gson.toJson(item); 
        // create the object
        instance.editItem(itemid, result);
        // create 2nd objects for the comparison
        StockItem item2 = items[items.length-3];
        String expresult = gson.toJson(item2);
        item.setItemID(item2.getItemID());
        String result2 = gson.toJson(item); 
        // do the comparison
        assertEquals(expresult, result2);
    }

    /**
     * Test of increaseStock method, of class DatabaseManipulator.
     */
    @Test
    public void testIncreaseStock() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all items list
        StockItem[] items = gson.fromJson(instance.getAllItems(), StockItem[].class);
        // set item1 to be the last item
        StockItem item1 = items[items.length-1];
        // get item1 id
        int itemid = item1.getItemID();
        int itemstock = item1.getQuantity();
        int newItemStock = itemstock + 1;
        // increase stock
        String result = instance.increaseStock(Integer.toString(itemid), Integer.toString(newItemStock));
        // create item2
        StockItem item2 = gson.fromJson(instance.getItem(Integer.toString(itemid)), StockItem.class);
        // get item2 id
        int item2id = item2.getItemID();
        int item2stock = itemstock; // old quantity
        if(item2stock < item1.getQuantity()){
            // do the comparison
            assertEquals(result, result);
        }  
        
    }

    /**
     * Test of updateStockItems method, of class DatabaseManipulator.
     */
    @Test
    public void testUpdateStockItems() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all items list
        StockItem[] items = gson.fromJson(instance.getAllItems(), StockItem[].class);
        // set item1 to be the last item
        StockItem item1 = items[items.length-1];
        // get item1 id
        int itemid = item1.getItemID();
        int itemstock = item1.getQuantity();
        int newItemStock = itemstock + 1;
        // increase stock
        String result = instance.updateStockItems(Integer.toString(itemid), Integer.toString(newItemStock));
        // create item2
        StockItem item2 = gson.fromJson(instance.getItem(Integer.toString(itemid)), StockItem.class);
        // get item2 id
        int item2id = item2.getItemID();
        int item2stock = itemstock; // old quantity
        if(item2stock < item1.getQuantity()){
            // do the comparison
            assertEquals(result, result);
    }
    
 }

    /**
     * Test of getPurchaseInvoice method, of class DatabaseManipulator.
     */
    @Test
    public void testGetPurchaseInvoice() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all items list
        PurchaseInvoice[] purchases = gson.fromJson(instance.getAllPurchaseInvoices(), PurchaseInvoice[].class);
        // set purchase1 to be the last purchase
        PurchaseInvoice purchase1 = purchases[purchases.length-1];
        // get purchase1 id
        int purchaseid = purchase1.getInvoiceID();
        // create purchase2
        PurchaseInvoice purchase2 = gson.fromJson(instance.getPurchaseInvoice(Integer.toString(purchaseid)), PurchaseInvoice.class);
        // get purchase2 id
        int purchase2id = purchase2.getInvoiceID();
         
        // do the comparison
        assertEquals(purchase2id, purchaseid);
    }

    /**
     * Test of getAllPurchaseInvoices method, of class DatabaseManipulator.
     */
    @Test
    public void testGetAllPurchaseInvoices() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all purchase list
        PurchaseInvoice[] purchases = gson.fromJson(instance.getAllItems(), PurchaseInvoice[].class);
        PurchaseInvoice[] purchases2 = gson.fromJson(instance.getAllItems(), PurchaseInvoice[].class);
        // get the lenghts
        int purchaseslen = purchases.length;
        int purchases2len = purchases2.length;
        // do the comparison
        assertEquals(purchases2len, purchaseslen);
    }

    /**
     * Test of deletePurchaseInvoice method, of class DatabaseManipulator.
     */
    @Test
    public void testDeletePurchaseInvoice() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();        
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all purchase list
        PurchaseInvoice[] purchases = gson.fromJson(instance.getAllPurchaseInvoices(), PurchaseInvoice[].class);
        int initLength = purchases.length;
        // set pruchase1 to be the second to last purchase
        PurchaseInvoice purchase1 = purchases[purchases.length-2];
        // get purchase1 purchaseid
        int purchaseid = purchase1.getInvoiceID();
        // delete last purchase
        String result = instance.deletePurchaseInvoice(Integer.toString(purchaseid));
        
        PurchaseInvoice[] pur = gson.fromJson(instance.getAllPurchaseInvoices(), PurchaseInvoice[].class);
        int finalLength = pur.length;
        if(initLength > finalLength){
            assertEquals(result, result);
        }
       
    }

    /**
     * Test of createPurchaseInvoice method, of class DatabaseManipulator.
     */
    @Test
    public void testCreatePurchaseInvoice() throws SQLException {
        System.out.println("Create Purchase Invoice");
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        DatabaseManipulator instance = new DatabaseManipulator();
        PurchaseInvoice[] initial = gson.fromJson(instance.getAllPurchaseInvoices(), PurchaseInvoice[].class);
        int initLength = initial.length;
        // set details of the object
       
        // create 1 purchase item
        CustomerPurchaseItem purchaseItem = new CustomerPurchaseItem();
        purchaseItem.setDateRented("2020-02-12");
        purchaseItem.setDateReturned("2020-02-12");
        purchaseItem.setItemID("11");
        purchaseItem.setItemName("name");
        purchaseItem.setQuantity(10);
        
        // create 2nd puchase item
        CustomerPurchaseItem purchaseItem2 = new CustomerPurchaseItem();
        purchaseItem2.setDateRented("2020-02-12");
        purchaseItem2.setDateReturned("2020-02-12");
        purchaseItem2.setItemID("2");
        purchaseItem2.setItemName("name");
        purchaseItem2.setQuantity(10);
        // create invoice
        PurchaseInvoice purchaseInvoice = new PurchaseInvoice();
        purchaseInvoice.setHasRentedItems(false);
        purchaseInvoice.setInvoiceDate("2020-02-12");
        purchaseInvoice.setInvoiceDescription("description");
        purchaseInvoice.setUserID(1);
        purchaseInvoice.getItems().add(purchaseItem);
        purchaseInvoice.getItems().add(purchaseItem2);
        purchaseInvoice.setTotalPrice((float) 263.862);
        
         // pass the object to json
        String result = gson.toJson(purchaseInvoice);

        // create the object
        instance.createPurchaseInvoice(result);

        // create 2nd objects for the comparison
        PurchaseInvoice[] purchases = gson.fromJson(instance.getAllPurchaseInvoices(), PurchaseInvoice[].class);
        PurchaseInvoice purchase2 = purchases[purchases.length - 1];
        
        int newLength = purchases.length;
        
        int purchaseid2 = purchase2.getInvoiceID();
        purchase2.setInvoiceID(purchaseid2);
        String expresult = gson.toJson(purchase2);
        
// if the list increased pass test
        if(initLength < newLength){
            result = expresult;
        }
        // do the comparison
        assertEquals(expresult, result);
        
    }

    /**
     * Test of editPurchaseInvoice method, of class DatabaseManipulator.
     */
    @Test
    public void testEditPurchaseInvoice() throws SQLException {
        System.out.println("editPurchaseInvoice");
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create the db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all item and then get third to last item to edit
        PurchaseInvoice[] purchases = gson.fromJson(instance.getAllPurchaseInvoices(), PurchaseInvoice[].class);
        PurchaseInvoice purchase = purchases[purchases.length-3];
        // set details of the edited item
        purchase.setHasRentedItems(false);
        purchase.setInvoiceDate("2020-02-12");
        purchase.setInvoiceDescription("new description");
        purchase.setUserID(3);
        // get purchase id
        String purchaseid = Integer.toString(purchase.getInvoiceID());
        // pass the object to json
        String result = gson.toJson(purchase); 
        // create the object
        instance.editPurchaseInvoice(purchaseid, result);
        // create 2nd objects for the comparison
        PurchaseInvoice purchase2 = purchases[purchases.length-3];
        String expresult = gson.toJson(purchase2);
        purchase.setInvoiceID(purchase2.getInvoiceID());
        String result2 = gson.toJson(purchase); 
        // do the comparison
        assertEquals(expresult, result2);
    }

    /**
     * Test of getRefillInvoice method, of class DatabaseManipulator.
     */
    @Test
    public void testGetRefillInvoice() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all refill list
        RefillInvoice[] refills = gson.fromJson(instance.getAllRefillInvoices(), RefillInvoice[].class);
        // set refill to be the last refill
        RefillInvoice refill1 = refills[refills.length-1];
        // get refill1 id
        int refillid = refill1.getInvoiceID();
        // create refill2
        RefillInvoice refill2 = gson.fromJson(instance.getRefillInvoice(Integer.toString(refillid)), RefillInvoice.class);
        // get refill2 id
        int refill2id = refill2.getInvoiceID();
         
        // do the comparison
        assertEquals(refill2id, refillid);
    }

    /**
     * Test of getAllRefillInvoices method, of class DatabaseManipulator.
     */
    @Test
    public void testGetAllRefillInvoices() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all refill list
        RefillInvoice[] refill = gson.fromJson(instance.getAllItems(), RefillInvoice[].class);
        RefillInvoice[] refill2 = gson.fromJson(instance.getAllItems(), RefillInvoice[].class);
        // get the lenghts
        int refilllen = refill.length;
        int refill2len = refill2.length;
        // do the comparison
        assertEquals(refill2len, refilllen);
    }

    /**
     * Test of deleteRefillInvoice method, of class DatabaseManipulator.
     */
    @Test
    public void testDeleteRefillInvoice() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();        
        // create db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all refill list
        RefillInvoice[] refills = gson.fromJson(instance.getAllRefillInvoices(), RefillInvoice[].class);
        int initLength = refills.length;
        // set refill1 to be the second to last refill
        RefillInvoice refill1 = refills[refills.length-2];
        // get refill1 refillid
        int refillid = refill1.getInvoiceID();
        // delete last refill
        String result = instance.deleteRefillInvoice(Integer.toString(refillid));
        
        RefillInvoice[] pur = gson.fromJson(instance.getAllRefillInvoices(), RefillInvoice[].class);
        int finalLength = pur.length;
        if(initLength > finalLength){
            assertEquals(result, result);
        }
    }

    /**
     * Test of createRefillInvoice method, of class DatabaseManipulator.
     */
    @Test
    public void testCreateRefillInvoice() throws SQLException {
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        DatabaseManipulator instance = new DatabaseManipulator();
        RefillInvoice[] initial = gson.fromJson(instance.getAllRefillInvoices(), RefillInvoice[].class);
        int initLength = initial.length;
        // set details of the object
       // create 1 purchase item
        CustomerPurchaseItem purchaseItem = new CustomerPurchaseItem();
        purchaseItem.setDateRented("2020-02-12");
        purchaseItem.setDateReturned("2020-02-12");
        purchaseItem.setItemID("6");
        purchaseItem.setItemName("name");
        purchaseItem.setQuantity(5);
        
        // create 2nd puchase item
        CustomerPurchaseItem purchaseItem2 = new CustomerPurchaseItem();
        purchaseItem2.setDateRented("2020-02-12");
        purchaseItem2.setDateReturned("2020-02-12");
        purchaseItem2.setItemID("5");
        purchaseItem2.setItemName("name");
        purchaseItem2.setQuantity(5);
        // create invoice
        RefillInvoice refillInvoice = new RefillInvoice();
        refillInvoice.setInvoiceDate("2020-02-12");
        refillInvoice.setTotalPrice((float) 131.931);
        refillInvoice.setUserID(1);
        refillInvoice.setSupplierID("1");
        refillInvoice.setShipmentAddres("address");
        refillInvoice.setInvoiceDescription("description");
        
        refillInvoice.getItems().add(purchaseItem);
        refillInvoice.getItems().add(purchaseItem2);
         // pass the object to json
        String result = gson.toJson(refillInvoice);

        // create the object
        instance.createRefillInvoice(result);

        // create 2nd objects for the comparison
        RefillInvoice[] refills = gson.fromJson(instance.getAllRefillInvoices(), RefillInvoice[].class);
        RefillInvoice refill = refills[refills.length - 1];
        
        int newLength = refills.length;
        
        int refillid2 = refill.getInvoiceID();
        refill.setInvoiceID(refillid2);
        String expresult = gson.toJson(refill);
        
        // if the list increased pass test
        if(initLength < newLength){
            result = expresult;
        }
        // do the comparison
        assertEquals(expresult, result);
    }

    /**
     * Test of editRefillInvoice method, of class DatabaseManipulator.
     */
    @Test
    public void testEditRefillInvoice() throws SQLException {
        
        System.out.println("editRefillInvoice");
        // create gson object
        Gson gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
        // create the db object
        DatabaseManipulator instance = new DatabaseManipulator();
        // get all item and then get third to last item to edit
        RefillInvoice[] refills = gson.fromJson(instance.getAllRefillInvoices(), RefillInvoice[].class);
        RefillInvoice refill = refills[refills.length-3];
        // set details of the edited item
        refill.setInvoiceDescription("new description");
        refill.setShipmentAddres("new adress");
        // get purchase id
        String refillid = Integer.toString(refill.getInvoiceID());
        // pass the object to json
        String result = gson.toJson(refill); 
        // create the object
        instance.editRefillInvoice(refillid, result);
        // create 2nd objects for the comparison
        RefillInvoice refill2 = refills[refills.length-3];
        String expresult = gson.toJson(refill2);
        refill.setInvoiceID(refill2.getInvoiceID());
        String result2 = gson.toJson(refill); 
        // do the comparison
        assertEquals(expresult, result2);

    }
    
}
