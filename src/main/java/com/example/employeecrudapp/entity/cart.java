package com.example.employeecrudapp.entity;

import jakarta.persistence.*;

@Entity
public class cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    @ManyToOne
    @JoinColumn(name="empid")
    employee emp;
    @ManyToOne
    @JoinColumn(name="itemid")
    item item;
    int quantity;
    public cart(){};

    public cart( employee emp, item item, int quantity) {

        this.emp = emp;
        this.item = item;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public employee getEmp() {
        return emp;
    }

    public void setEmp(employee emp) {
        this.emp = emp;
    }

    public item getItem() {
        return item;
    }

    public void setItem(item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

