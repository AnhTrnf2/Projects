/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Tuan Anh Tran
 */
public class Message {
    private int messageID;
    private Date sent_date;
    private String details;
    private User sender;
    private User receiver;

    public Message() {
    }

    public Message(int messageID, Date sent_date, String details, User sender, User receiver) {
        this.messageID = messageID;
        this.sent_date = sent_date;
        this.details = details;
        this.sender = sender;
        this.receiver = receiver;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public Date getSent_date() {
        return sent_date;
    }

    public void setSent_date(Date sent_date) {
        this.sent_date = sent_date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    
}
