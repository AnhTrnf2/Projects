/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Contact;
import model.User;

/**
 *
 * @author Tuan Anh Tran
 */
public class ContactDBContext extends DBContext {
    public ArrayList<Contact> getContactsOfUser(String ownerID) {
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            String query = "SELECT c.contactID, u.full_name, c.[status], c.note\n"
                    + "FROM\n"
                    + "	Contacts AS c \n"
                    + "	LEFT JOIN [User] as u\n"
                    + "	ON c.contactID = u.userID\n"
                    + "WHERE c.userID = ?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, ownerID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                String contactID = rs.getString("contactID");
                String note = rs.getString("note");
                boolean status = rs.getBoolean("status");
                String contact_name = rs.getString("full_name");
                User owner = new User();
                owner.setUserID(contactID);
                User contact = new User();
                contact.setUserID(contactID);
                contact.setFull_name(contact_name);
                Contact c = new Contact(owner, note, status, contact);
                contacts.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contacts;
    }
    
    public void addContact(String userID, String contactID, String note) {
        try {
            String query = "INSERT INTO [dbo].[Contacts]\n" +
                    "           ([userID]\n" +
                    "           ,[contactID]\n" +
                    "           ,[status]\n" +
                    "           ,[note])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, userID);
            stm.setString(2, contactID);
            stm.setBoolean(3, false);
            if(note.isEmpty()){
                stm.setNull(4, Types.NVARCHAR);
            }
            else {
                stm.setString(4, note);
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContactDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
