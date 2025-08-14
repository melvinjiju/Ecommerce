package com.example.employeecrudapp.services;

import com.example.employeecrudapp.dao.empdaoimp;
import com.example.employeecrudapp.entity.*;
import com.example.employeecrudapp.repositories.cartrepo;
import com.example.employeecrudapp.repositories.itemrepo;
import com.example.employeecrudapp.repositories.userrepo;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class service {
    private final itemrepo itemrepo;
    private final EntityManager entitymanager;
    private final userrepo userrepo;
    private final cartrepo cartrepo;
    private empdaoimp helper;

    {
    }

    public service(itemrepo itemrepo, EntityManager entitymanager, userrepo userrepo, cartrepo cartrepo, empdaoimp empdaoimp) {
        this.itemrepo = itemrepo;
        this.entitymanager = entitymanager;
        this.cartrepo = cartrepo;
        this.userrepo = userrepo;
        helper = empdaoimp;
    }

    public List<employee> getall() {
        return helper.getallinfo();
    }

    public List<employee> getone(int id) {
        return helper.getone(id);
    }

    @Transactional
    public void insert(employee emp) {
        helper.saveinfo(emp);
    }

    @Transactional
    public void update(int id, String name) {
        helper.update(id, name);
    }

    @Transactional
    public void partupdate(int id, Map<String, Object> mape) throws JsonMappingException {
        helper.partupdate(id, mape);
    }

    @Transactional
    public void additem(item item) {
        itemrepo.save(item);
    }

    public List<item> viewitems() {
        return itemrepo.findAll();
    }

    @Transactional
    public void addcartitem(String item1, int quantity) {
        item item = itemrepo.findByName(item1);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userdetail userdetail = (Userdetail) auth.getPrincipal();
        var emp = userrepo.findByEmail(userdetail.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        cart cart = new cart(emp, item, quantity);
        cartrepo.save(cart);

    }

    public List<usercart> viewshoppingitem() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userdetail userdetail = (Userdetail) auth.getPrincipal();
        var emp = userrepo.findByEmail(userdetail.getUsername());
        int userId = emp.orElseThrow().getId();
        List<cart> shoppingcart = cartrepo.findByEmp_Id(userId);
        List<usercart> usercarts = new ArrayList<usercart>();
        for (cart result : shoppingcart) {
            usercart ucart = new usercart(result.getItem().getName(), result.getQuantity());
            usercarts.add(ucart);

        }

return usercarts;
    }
    public List<admincart> viewallcart(){
        List<admincart> maincart=new ArrayList<admincart>();
        List<cart> allcarts=cartrepo.findAll();
        for(cart result :allcarts){
            admincart admincart = new admincart(result.getEmp().getFirstname(),result.getItem().getName(), result.getQuantity());
            maincart.add(admincart);

        }
        return maincart;
    }

}


