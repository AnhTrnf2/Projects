/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import model.User;

/**
 *
 * @author Tuan Anh Tran
 */
public class MessageDBContext extends DBContext {
    public ArrayList<Message> getIncomingMessages(String receiverID) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            String query = "SELECT m.senderID, m.messageID, m.details, m.date_created\n"
                    + "FROM\n"
                    + "	[Messages] AS m\n"
                    + "	LEFT JOIN [User] AS u\n"
                    + "	ON m.senderID = u.userID\n"
                    + "WHERE\n"
                    + "	m.receiverID = ?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, receiverID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                String senderID = rs.getString("senderID");
                User sender = new User();
                sender.setUserID(senderID);
                User receiver = new User();
                receiver.setUserID(receiverID);
                int messageID = rs.getInt("messageID");
                String details = rs.getString("details");
                Date date_created = rs.getDate("date_created");
                Message m = new Message(messageID, date_created, details, sender, receiver);
                messages.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }
    
    public ArrayList<Message> getOutgoingMessages(String senderID) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            String query = "SELECT m.receiverID, m.messageID, m.details, m.date_created\n"
                    + "FROM\n"
                    + "	[Messages] AS m\n"
                    + "	LEFT JOIN [User] AS u\n"
                    + "	ON m.receiverID = u.userID\n"
                    + "WHERE\n"
                    + "	m.senderID = ?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, senderID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                User sender = new User();
                sender.setUserID(senderID);
                String receiverID = rs.getString("receiverID");
                User receiver = new User();
                receiver.setUserID(receiverID);
                int messageID = rs.getInt("messageID");
                String details = rs.getString("details");
                Date date_created = rs.getDate("date_created");
                Message m = new Message(messageID, date_created, details, sender, receiver);
                messages.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }
    
    public void addMessage(String senderID, String receiverID, String details) {
        try {
            String query = "INSERT INTO [dbo].[Messages]\n"
                    + "           ([receiverID]\n"
                    + "           ,[details]\n"
                    + "           ,[senderID]\n"
                    + "           ,[date_created])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            java.util.Date date = new java.util.Date();
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, receiverID);
            stm.setString(2, details);
            stm.setString(3, senderID);
            stm.setDate(4, Date.valueOf(format.format(date)));
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MessageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
