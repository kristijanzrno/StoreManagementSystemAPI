package store.management.system;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author kristijanzrno
 */
@Path("StoreManagement")
public class StoreManagementResource {

    @Context
    private UriInfo context;


    public StoreManagementResource() {}
    
    public String getJSON(){
        return "";
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
        //todo
       return new DatabaseManipulator().getUser(""+userID);
    }
    
    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        //todo
       return "";
    }
    
    @GET
    @Path("/deleteUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") int userID) {
        //todo
       return "";
    }
    
    @GET
    @Path("/createUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(
            @QueryParam("username") String username, 
            @QueryParam("password") String password, 
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("email") String email,
            @QueryParam("address") String address,
            @QueryParam("phoneNumber") String phoneNumber,
            @QueryParam("accountType") String accountType){
        //todo
       return "";
    }
    
    @GET
    @Path("/editUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(
            @PathParam("id") int userID,
            @QueryParam("username") String username, 
            @QueryParam("password") String password, 
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("email") String email,
            @QueryParam("address") String address,
            @QueryParam("phoneNumber") String phoneNumber,
            @QueryParam("accountType") String accountType){
        //todo
       return "";
    }
    
    /*
    ************************
    TOP LEVEL SUPPLIER FUNCTIONS
    ************************
    */
    
    @GET
    @Path("/getSupplier/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSupplier(@PathParam("id") int supplierID) {
        //todo
       return "";
    }
    
    @GET
    @Path("/getAllSuppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllSuppliers() {
        //todo
       return "";
    }
    
    @GET
    @Path("/deleteSupplier/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSupplier(@PathParam("id") int supplierID) {
        //todo
       return "";
    }
    
    @GET
    @Path("/createSupplier")
    @Produces(MediaType.APPLICATION_JSON)
    public String createSupplier(
            @QueryParam("supplierName") String supplierName, 
            @QueryParam("supplierShippmentAddress") String supplierShippmentAddress, 
            @QueryParam("supplierAddress") String supplierAddress,
            @QueryParam("supplierEmail") String supplierEmail,
            @QueryParam("supplierPhoneNumber") String supplierPhoneNumber,
            @QueryParam("supplierDescription") String supplierDescription){
        //Note, i missed the supplierName column when creating the SQL databasem, need to add it later on
        //todo
       return "";
    }
    
    @GET
    @Path("/editSupplier/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editSupplier(
            @PathParam("id") int supplierID,
            @QueryParam("supplierName") String supplierName, 
            @QueryParam("supplierShippmentAddress") String supplierShippmentAddress, 
            @QueryParam("supplierAddress") String supplierAddress,
            @QueryParam("supplierEmail") String supplierEmail,
            @QueryParam("supplierPhoneNumber") String supplierPhoneNumber,
            @QueryParam("supplierDescription") String supplierDescription){
        //todo
       return "";
    }
    
    /*
    ************************
    TOP LEVEL STOCK ITEMS FUNCTIONS
    ************************
    */
    
    @GET
    @Path("/getItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItem(@PathParam("id") int itemID) {
        //todo
       return "";
    }
    
    @GET
    @Path("/getAllItems")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllItems() {
        //todo
       return "";
    }
    
    @GET
    @Path("/deleteItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteItem(@PathParam("id") int itemID) {
        //todo
       return "";
    }
    
    @GET
    @Path("/createItem")
    @Produces(MediaType.APPLICATION_JSON)
    public String createSupplier(
            @QueryParam("itemDescription") String itemDescription, 
            @QueryParam("itemQuantity") String itemQuantity,
            @QueryParam("itemType") String itemType,
            @QueryParam("itemManufacturerPartNum") String itemManufacturerPartNum,
            @QueryParam("itemPurchaseInfo") String itemPurchaseInfo,
            @QueryParam("itemDescriptionOfSale") String itemDescriptionOfSale,
            @QueryParam("itemCost") String itemCost,
            @QueryParam("itemSalesPrice") String itemSalesPrice,
            @QueryParam("itemPrefferedSupplier") String itemPrefferedSupplier,
            @QueryParam("itemVAT") String itemVAT,
            @QueryParam("itemLastModifiedData") String itemLastModifiedData){
        //todo
       return "";
    }
    
    @GET
    @Path("/editItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editItem(
            @PathParam("id") int itemID,
            @QueryParam("itemDescription") String itemDescription, 
            @QueryParam("itemQuantity") String itemQuantity,
            @QueryParam("itemType") String itemType,
            @QueryParam("itemManufacturerPartNum") String itemManufacturerPartNum,
            @QueryParam("itemPurchaseInfo") String itemPurchaseInfo,
            @QueryParam("itemDescriptionOfSale") String itemDescriptionOfSale,
            @QueryParam("itemCost") String itemCost,
            @QueryParam("itemSalesPrice") String itemSalesPrice,
            @QueryParam("itemPrefferedSupplier") String itemPrefferedSupplier,
            @QueryParam("itemVAT") String itemVAT,
            @QueryParam("itemLastModifiedData") String itemLastModifiedData){
        //todo
       return "";
    }
    
    @GET
    @Path("/updateStock/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateStock(@QueryParam("noOfItems") String noOfItems){
        //todo add noOfItems to current number of items
        return "";
    }
    
    /*
    ************************
    TOP LEVEL PURCHASE INVOICES FUNCTIONS
    ************************
    */
    
    @GET
    @Path("/getPurchaseInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPurchaseInvoice(@PathParam("id") int invoiceID) {
        //todo
       return "";
    }
    
    @GET
    @Path("/getAllPurchaseInvoices")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPurchaseInvoices() {
        //todo
       return "";
    }
    
    @GET
    @Path("/deletePurchaseInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePurchaseInvoice(@PathParam("id") int invoiceID) {
        //todo
       return "";
    }
    
    @GET
    @Path("/createPurchaseInvoice")
    @Produces(MediaType.APPLICATION_JSON)
    public String createPurchaseInvoice(
            @QueryParam("userID") String userID,
            @QueryParam("invoiceDate") String invoiceDate,
            @QueryParam("dateRented") String dateRented,
            @QueryParam("dateReturned") String dateReturned,
            @QueryParam("invoiceRent") String invoiceRent,
            @QueryParam("totalPrice") String totalPrice){
        //todo later, when everything else is implemented
       return "";
    }
    
    @GET
    @Path("/editPurchaseInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editPurchaseInvoice(
            @PathParam("id") int invoiceID,
            @QueryParam("userID") String userID,
            @QueryParam("invoiceDate") String invoiceDate,
            @QueryParam("dateRented") String dateRented,
            @QueryParam("dateReturned") String dateReturned,
            @QueryParam("invoiceRent") String invoiceRent,
            @QueryParam("totalPrice") String totalPrice){
        //todo later, when everything else is implemented
       return "";
    }
    
    /*
    ************************
    TOP LEVEL REFILL INVOICES FUNCTIONS
    ************************
    */
    
    @GET
    @Path("/getRefillInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRefillInvoice(@PathParam("id") int invoiceID) {
        //todo
       return "";
    }
    
    @GET
    @Path("/getAllRefillInvoices")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRefillInvoices() {
        //todo
       return "";
    }
    
    @GET
    @Path("/deleteRefillInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteRefillInvoice(@PathParam("id") int invoiceID) {
        //todo
       return "";
    }
    
    @GET
    @Path("/createRefillInvoice")
    @Produces(MediaType.APPLICATION_JSON)
    public String createRefillInvoice(
            @QueryParam("issuerID") String issuerID, 
            @QueryParam("invoiceDate") String invoiceDate,
            @QueryParam("invoiceDescription") String invoiceDescription,
            @QueryParam("supplierID") String supplierID){
        //todo later, when everything else is implemented
       return "";
    }
    
    @GET
    @Path("/editRefillInvoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editRefillInvoice(
            @PathParam("id") int invoiceID,
            @QueryParam("issuerID") String issuerID, 
            @QueryParam("invoiceDate") String invoiceDate,
            @QueryParam("invoiceDescription") String invoiceDescription,
            @QueryParam("supplierID") String supplierID){
        //todo later, when everything else is implemented
       return "";
    }
    
   
}
