package store.management.system;

import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author kristijanzrno
 */
@Path("StoreManagement")
public class StoreManagementResource {

    @Context
    private UriInfo context;
    private DatabaseManipulator databaseManipulator;
    public StoreManagementResource() throws SQLException {
        databaseManipulator = new DatabaseManipulator();
    }
    
    @GET
    public String getTest(){
        return "test";
    }

    /*
    ************************
    LOGIN
    ************************
     */
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@QueryParam("username") String username, @QueryParam("password") String password) throws SQLException {
        return new AuthUtils().authenticateCustomer(username, password);
    }
    
    /*
    ************************
    TOP LEVEL USER FUNCTIONS
    ************************
     */
    @GET
    @Path("/getUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id") int userID) throws SQLException {
        String result = databaseManipulator.getUser("" + userID);
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() throws SQLException {
        String result = databaseManipulator.getAllUsers();
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/deleteUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") int userID) throws SQLException {
        String result = databaseManipulator.deleteUser("" + userID);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(String json) throws SQLException {
        String result = databaseManipulator.createUser(json);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/editUser/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editUser(@PathParam("id") int userID, String json) throws SQLException {
        String result = databaseManipulator.editUser("" + userID, json);
        databaseManipulator.closeConnection();
        return result;
    }

    /*
    ************************
    TOP LEVEL SUPPLIER FUNCTIONS
    ************************
     */
    @GET
    @Path("/getSupplier/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSupplier(@PathParam("id") int supplierID) throws SQLException {
        String result = databaseManipulator.getSupplier("" + supplierID);
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/getAllSuppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllSuppliers() throws SQLException {
        String result = databaseManipulator.getAllSuppliers();
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/deleteSupplier/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSupplier(@PathParam("id") int supplierID) throws SQLException {
        String result = databaseManipulator.deleteSupplier("" + supplierID);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/createSupplier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createSupplier(String json) throws SQLException {
        String result = databaseManipulator.createSupplier(json);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/editSupplier/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editSupplier(@PathParam("id") int supplierID, String json) throws SQLException {
        String result = databaseManipulator.editSupplier("" + supplierID, json);
        databaseManipulator.closeConnection();
        return result;
    }

    /*
    ************************
    TOP LEVEL STOCK ITEMS FUNCTIONS
    ************************
     */
    @GET
    @Path("/getItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItem(@PathParam("id") int itemID) throws SQLException {
        String result = databaseManipulator.getItem("" + itemID);
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/getAllItems")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllItems() throws SQLException {
        String result = databaseManipulator.getAllItems();
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/deleteItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteItem(@PathParam("id") int itemID) throws SQLException {
        String result = databaseManipulator.deleteItem("" + itemID);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/createItem")
    @Produces(MediaType.APPLICATION_JSON)
    public String createItem(String json) throws SQLException {
        String result = databaseManipulator.createItem(json);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/editItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editItem(@PathParam("id") int itemID, String json) throws SQLException {
        String result = databaseManipulator.editItem("" + itemID, json);
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/updateStock/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateStock(@PathParam("id") String itemID, @QueryParam("noOfItems") String noOfItems) throws SQLException {
        String result = databaseManipulator.increaseStock(itemID, noOfItems);
        databaseManipulator.closeConnection();
        return result;
    }

    /*
    ************************
    TOP LEVEL PURCHASE INVOICES FUNCTIONS
    ************************
     */
    @GET
    @Path("/getPurchaseInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPurchaseInvoice(@PathParam("id") int invoiceID) throws SQLException {
        String result = databaseManipulator.getPurchaseInvoice(""+invoiceID);
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/getAllPurchaseInvoices")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPurchaseInvoices() throws SQLException {
        String result = databaseManipulator.getAllPurchaseInvoices();
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/deletePurchaseInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePurchaseInvoice(@PathParam("id") int invoiceID) throws SQLException {
        String result = databaseManipulator.deletePurchaseInvoice("" + invoiceID);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/createPurchaseInvoice")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createPurchaseInvoice(String json) throws SQLException {
        String result = databaseManipulator.createPurchaseInvoice(json);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/editPurchaseInvoice/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editPurchaseInvoice(@PathParam("id") int invoiceID, String json) throws SQLException {
        String result = databaseManipulator.editPurchaseInvoice("" + invoiceID, json);
        databaseManipulator.closeConnection();
        return result;
    }

    /*
    ************************
    TOP LEVEL REFILL INVOICES FUNCTIONS
    ************************
     */
    @GET
    @Path("/getRefillInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRefillInvoice(@PathParam("id") int invoiceID) throws SQLException {
        String result = databaseManipulator.getRefillInvoice("" + invoiceID);
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/getAllRefillInvoices")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRefillInvoices() throws SQLException {
        String result = databaseManipulator.getAllRefillInvoices();
        databaseManipulator.closeConnection();
        return result;
    }

    @GET
    @Path("/deleteRefillInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteRefillInvoice(@PathParam("id") int invoiceID) throws SQLException {
        String result = databaseManipulator.deleteRefillInvoice("" + invoiceID);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/createRefillInvoice")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createRefillInvoice(String json) throws SQLException {
        String result = databaseManipulator.createRefillInvoice(json);
        databaseManipulator.closeConnection();
        return result;
    }

    @POST
    @Path("/editRefillInvoice/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editRefillInvoice(@PathParam("id") int invoiceID, String json) throws SQLException {
        String result = databaseManipulator.editRefillInvoice("" + invoiceID, json);
        databaseManipulator.closeConnection();
        return result;
    }
    
     /*
    ************************
    DASHBOARD FUNCTIONS
    ************************
     */
    
    @GET
    @Path("/dashboardQuery")
    @Produces(MediaType.APPLICATION_JSON)
    public String dashboardQuery(@QueryParam("query") String query) throws SQLException {
        String result = databaseManipulator.dashboardQuery(query);
        databaseManipulator.closeConnection();
        return result;
    }

}
