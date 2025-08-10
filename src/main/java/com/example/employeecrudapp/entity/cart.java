package com.example.employeecrudapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    int userid;
    int itemid;
    int quantity;
    public cart(){};

    public cart(int userid, int itemid, int quantity) {
        this.id = id;
        this.userid = userid;
        this.itemid = itemid;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
