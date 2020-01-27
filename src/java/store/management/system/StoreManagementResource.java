/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.management.system;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author kristijanzrno
 */
@Path("StoreManagement")
public class StoreManagementResource {

    Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;

    String user = "student";
    String pass = "student";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StoreManagementResource
     */
    public StoreManagementResource() {
    }

    /**
     * Retrieves representation of an instance of
     * store.management.system.StoreManagementResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        try {
            //TODO return proper representation object
            new DatabaseManipulator();
        } catch (SQLException ex) {
            Logger.getLogger(StoreManagementResource.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "";
    }

    /**
     * PUT method for updating or creating an instance of
     * StoreManagementResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
