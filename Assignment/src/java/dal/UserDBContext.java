/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.User;

/**
 *
 * @author Tuan Anh Tran
 */
public class UserDBContext extends DBContext {
    public User getUser(String phone_number) {
        try {
            String query = "SELECT\n"
                    + "	u.userID,\n"
                    + "	u.full_name,\n"
                    + "	u.bio,\n"
                    + "	u.date_created,\n"
                    + "	u.profile_image_path\n"
                    + "FROM\n"
                    + "	Account AS a\n"
                    + "	LEFT JOIN [User] AS u\n"
                    + "	ON a.phone_number = u.userID\n"
                    + "WHERE\n"
                    + "	a.phone_number = ?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, phone_number);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                String userID = rs.getString("userID");
                String full_name = rs.getString("full_name");
                String bio = rs.getString("bio");
                Timestamp date_created = rs.getTimestamp("date_created");
                String profile_image_path = rs.getString("profile_image_path");
                Account account = new Account();
                account.setPhone_number(phone_number);
                User user = new User(userID, full_name, bio, date_created, profile_image_path, account);
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
