package com.example.employeecrudapp.entity;

public class usercart {
    String itemname;
    int quantity;
    public usercart(){};

    public int getQuantity() {
        return quantity;
    }

    public usercart(String itemname, int quantity) {
        this.itemname = itemname;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
