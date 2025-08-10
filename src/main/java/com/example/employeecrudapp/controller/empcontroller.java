package com.example.employeecrudapp.controller;

import com.example.employeecrudapp.entity.*;
import com.example.employeecrudapp.services.jwtservice;
import com.example.employeecrudapp.services.service;
import com.example.employeecrudapp.repositories.userrepo;
import jakarta.persistence.EntityManager;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class empcontroller {
    private final EntityManager entitymanager;

    private final service helper;
    private final jwtservice jwtservice;;
    private final PasswordEncoder passwordencorder;
    private final AuthenticationManager authenticationManager;
    private final userrepo repo;

    public empcontroller(EntityManager entitymanager, service helper, jwtservice jwtservice, PasswordEncoder passwordencorder, AuthenticationManager authenticationManager, userrepo repo) {
        this.entitymanager = entitymanager;

        this.helper = helper;
        this.jwtservice = jwtservice;

        this.passwordencorder = passwordencorder;
        this.authenticationManager = authenticationManager;
        this.repo = repo;
    }

    @PostMapping ("/register")

    public String registerAccount(@RequestBody employee emp) {
        // Encode password before saving
        String encodedPassword = passwordencorder.encode(emp.getPassword());
        emp.setPassword(encodedPassword);

        helper.insert(emp); // Now saves employee with encoded password

        Userdetail user = new Userdetail(emp);
        return jwtservice.generatetoken(emp.getRole(), user);
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody signin signin){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signin.getEmail(),signin.getPassword()));
        var empOptional = repo.findByEmail(signin.getEmail());
        employee emp = empOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Userdetail user = new Userdetail(emp);
        return jwtservice.generatetoken(emp.getRole(), user);





    }
    @PostMapping("/admin/additem")
    @PreAuthorize("hasRole('ADMIN')")
    public void adminDashboard(@RequestBody item item) {
       helper.additem(item);
    }

   @GetMapping("/viewitems")
   public List<item> view(){
        return helper.viewitems();


   }




    @GetMapping("/user/dashboard")
    @PreAuthorize("hasRole('USER')")
    public String userDashboard() {
        return "Hello User!";
    }

    @PostMapping("/additemtocart")
    public void addcart(@RequestBody itemreq itemreq){
        helper.addcartitem(itemreq.getItem(), itemreq.getQuantity());



    }
    @GetMapping("/viewcart")
    public List<usercart> viewcart(){
        return helper.viewshoppingitem();
    }
}
