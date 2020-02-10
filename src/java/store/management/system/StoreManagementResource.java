package store.management.system;

import java.sql.SQLException;
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

    public StoreManagementResource() {
    }
    
    @GET
    public String getTest(){
        return "test";
    }

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
        return new DatabaseManipulator().getUser("" + userID);
    }

    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() throws SQLException {
        return new DatabaseManipulator().getAllUsers();
    }

    @GET
    @Path("/deleteUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") int userID) throws SQLException {
        return new DatabaseManipulator().deleteUser("" + userID);
    }

    @POST
    @Path("/createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(String json) throws SQLException {
        return new DatabaseManipulator().createUser(json);
    }

    @POST
    @Path("/editUser/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editUser(@PathParam("id") int userID, String json) throws SQLException {
        return new DatabaseManipulator().editUser("" + userID, json);
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
        return new DatabaseManipulator().getSupplier("" + supplierID);
    }

    @GET
    @Path("/getAllSuppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllSuppliers() throws SQLException {
        return new DatabaseManipulator().getAllSuppliers();
    }

    @GET
    @Path("/deleteSupplier/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSupplier(@PathParam("id") int supplierID) throws SQLException {
        return new DatabaseManipulator().deleteSupplier("" + supplierID);
    }

    @POST
    @Path("/createSupplier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createSupplier(String json) throws SQLException {
        return new DatabaseManipulator().createSupplier(json);
    }

    @POST
    @Path("/editSupplier/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editSupplier(@PathParam("id") int supplierID, String json) throws SQLException {
        return new DatabaseManipulator().editSupplier("" + supplierID, json);
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
        return new DatabaseManipulator().getItem("" + itemID);
    }

    @GET
    @Path("/getAllItems")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllItems() throws SQLException {
        return new DatabaseManipulator().getAllItems();
    }

    @GET
    @Path("/deleteItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteItem(@PathParam("id") int itemID) throws SQLException {
        return new DatabaseManipulator().deleteItem("" + itemID);
    }

    @POST
    @Path("/createItem")
    @Produces(MediaType.APPLICATION_JSON)
    public String createItem(String json) throws SQLException {
        return new DatabaseManipulator().createItem(json);
    }

    @POST
    @Path("/editItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editItem(@PathParam("id") int itemID, String json) throws SQLException {
        return new DatabaseManipulator().editItem("" + itemID, json);
    }

    @GET
    @Path("/updateStock/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateStock(@PathParam("id") String itemID, @QueryParam("noOfItems") String noOfItems) throws SQLException {
        return new DatabaseManipulator().updateStock(itemID, noOfItems);
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
        return new DatabaseManipulator().getPurchaseInvoice(""+invoiceID);
    }

    @GET
    @Path("/getAllPurchaseInvoices")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPurchaseInvoices() throws SQLException {
        return new DatabaseManipulator().getAllPurchaseInvoices();
    }

    @GET
    @Path("/deletePurchaseInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePurchaseInvoice(@PathParam("id") int invoiceID) throws SQLException {
        return new DatabaseManipulator().deletePurchaseInvoice("" + invoiceID);
    }

    @POST
    @Path("/createPurchaseInvoice")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createPurchaseInvoice(String json) throws SQLException {
        return new DatabaseManipulator().createPurchaseInvoice(json);
    }

    @POST
    @Path("/editPurchaseInvoice/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editPurchaseInvoice(@PathParam("id") int invoiceID, String json) throws SQLException {
        return new DatabaseManipulator().editPurchaseInvoice("" + invoiceID, json);
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
        return new DatabaseManipulator().getRefillInvoice("" + invoiceID);
    }

    @GET
    @Path("/getAllRefillInvoices")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRefillInvoices() throws SQLException {
        return new DatabaseManipulator().getAllRefillInvoices();
    }

    @GET
    @Path("/deleteRefillInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteRefillInvoice(@PathParam("id") int invoiceID) throws SQLException {
        return new DatabaseManipulator().deleteRefillInvoice("" + invoiceID);
    }

    @POST
    @Path("/createRefillInvoice")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createRefillInvoice(String json) throws SQLException {
        return new DatabaseManipulator().createRefillInvoice(json);
    }

    @POST
    @Path("/editRefillInvoice/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editRefillInvoice(@PathParam("id") int invoiceID, String json) throws SQLException {
        return new DatabaseManipulator().editRefillInvoice("" + invoiceID, json);
    }

}
