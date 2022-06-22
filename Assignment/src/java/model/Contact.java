/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Tuan Anh Tran
 */
public class Contact {
    private User owner;
    private String note;
    private boolean status;
    private User contact;

    public Contact() {
    }

    public Contact(User owner, String note, boolean status, User contact) {
        this.owner = owner;
        this.note = note;
        this.status = status;
        this.contact = contact;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getContact() {
        return contact;
    }

    public void setContact(User contact) {
        this.contact = contact;
    }

    
}
