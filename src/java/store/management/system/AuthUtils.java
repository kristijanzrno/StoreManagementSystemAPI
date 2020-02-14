/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.management.system;

import Data.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;

/**
 *
 * @author kristijanzrno
 */
public class AuthUtils {

    // Authentication todo
    Gson gson;

    public AuthUtils() {
        gson = new GsonBuilder().setFieldNamingStrategy(f -> f.getName().toLowerCase()).create();
    }

    public String authenticateCustomer(String username, String password) throws SQLException {
        String data = new DatabaseManipulator().getUserWithUsername(username);
        try {
            User user = gson.fromJson(data, User.class);
            if (user.getPassword().equals(password) && user.getAccountType() >= 1) {
                return data;
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    public String authenticateEmployee(String username, String password) throws SQLException {
        String data = new DatabaseManipulator().getUserWithUsername(username);
        try {
            User user = gson.fromJson(data, User.class);
            if (user.getPassword().equals(password) && user.getAccountType() >= 2) {
                return data;
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    public String authenticateAdmin(String username, String password) throws SQLException {
        String data = new DatabaseManipulator().getUserWithUsername(username);
        try {
            User user = gson.fromJson(data, User.class);
            if (user.getPassword().equals(password) && user.getAccountType() >= 3) {
                return data;
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }
}
