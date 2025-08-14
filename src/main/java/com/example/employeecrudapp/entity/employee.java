package com.example.employeecrudapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class employee {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int id;
    @Column(name="first_name")
     String firstname;
    @Column(name="last_name")
     String lastname;
    @Column(name="email")
     String email;
    @Column(name="password")
    String password;
    @Column(name="role")
    String role;
    public employee(){};
    public employee(int id, String firstname, String lastname, String email,String password,String role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password=password;
        this.role=role;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
