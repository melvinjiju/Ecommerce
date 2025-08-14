package com.example.employeecrudapp.entity;

public class admincart {
    String customername;
    String item;
    int quantity;

    public admincart() {
    }

    public admincart(String customername, String item, int quantity) {
        this.customername = customername;
        this.item = item;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
