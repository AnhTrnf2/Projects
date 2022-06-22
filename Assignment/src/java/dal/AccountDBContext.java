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
public class AccountDBContext extends DBContext {
    public Account getAccount(String phone_number, String password) {
        try {
            String query = "SELECT [phone_number], [password] FROM Account WHERE [phone_number] = ? AND [password] = ?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, phone_number);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setUserID(phone_number);
                Account account = new Account(phone_number, password, user);
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void addAccount(String phone_number, String password, String full_name, String profile_image_path) {
        try {
            String account_query = "INSERT INTO [dbo].[Account]\n" +
                    "           ([phone_number]\n" +
                    "           ,[password])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
            String user_query = "INSERT INTO [dbo].[User]\n"
                    + "           ([userID]\n"
                    + "           ,[full_name]\n"
                    + "           ,[bio]\n"
                    + "           ,[date_created]\n"
                    + "           ,[profile_image_path])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            connection.setAutoCommit(false);
            PreparedStatement stm_account = connection.prepareStatement(account_query);
            stm_account.setString(1, phone_number);
            stm_account.setString(2, password);
            stm_account.executeUpdate();
            
            PreparedStatement stm_user = connection.prepareStatement(user_query);
            stm_user.setString(1, phone_number);
            stm_user.setString(2, full_name);
            stm_user.setNull(3, Types.NVARCHAR);
            Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
            stm_user.setTimestamp(4, timestamp);
            stm_user.setString(5, profile_image_path);
            stm_user.executeUpdate();
            
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
