/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Tuan Anh Tran
 */
public class User {
    private String userID;
    private String full_name;
    private String bio;
    private Timestamp date_created;
    private String profile_image_path;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    private Account account;
    
    public User() {
    }

    public User(String userID, String full_name, String bio, Timestamp date_created, String profile_image_path, Account account) {
        this.userID = userID;
        this.full_name = full_name;
        this.bio = bio;
        this.date_created = date_created;
        this.profile_image_path = profile_image_path;
        this.account = account;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }

    public String getProfile_image_path() {
        return profile_image_path;
    }

    public void setProfile_image_path(String profile_image_path) {
        this.profile_image_path = profile_image_path;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    
}
