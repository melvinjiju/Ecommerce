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
    private empdaoimp helper;{}
    public service(itemrepo itemrepo, EntityManager entitymanager, userrepo userrepo, cartrepo cartrepo, empdaoimp empdaoimp){
        this.itemrepo = itemrepo;
        this.entitymanager = entitymanager;
        this.cartrepo = cartrepo;
        this.userrepo = userrepo;
        helper=empdaoimp;
    }
    public List<employee> getall(){
        return helper.getallinfo();
    }
    public List<employee>getone(int id){
        return helper.getone(id);
    }
    @Transactional
    public void insert(employee emp){
        helper.saveinfo(emp);
    }
    @Transactional
    public void update(int id,String name){
        helper.update(id,name);
    }
    @Transactional
    public void partupdate(int id, Map<String,Object> mape) throws JsonMappingException {
        helper.partupdate(id,mape);
}
@Transactional
public void additem(item item){
        itemrepo.save(item);
}
public List<item> viewitems(){
        return itemrepo.findAll();
}
@Transactional
public void addcartitem(String item1,int quantity){
  item item = itemrepo.findByName(item1);
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Userdetail userdetail = (Userdetail) auth.getPrincipal();
    var emp = userrepo.findByEmail(userdetail.getUsername());
    cart cart = new cart(emp.orElseThrow().getId(),item.getId(),quantity);
    cartrepo.save(cart);



}
    public List<usercart> viewshoppingitem(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userdetail userdetail = (Userdetail) auth.getPrincipal();
        var emp = userrepo.findByEmail(userdetail.getUsername());
        int userId = emp.orElseThrow().getId();
        System.out.println("result here");

        String sql = "SELECT i.name, c.quantity FROM cart c JOIN item i ON c.itemid = i.id WHERE c.userid = :userId";

        List<Object[]> results = entitymanager.createNativeQuery(sql)
                .setParameter("userId", userId)
                .getResultList();

        List<usercart> usercartList = new ArrayList<>();
        for (Object[] row : results) {
            String itemName = (String) row[0];
            System.out.println(itemName);
            int quantity = ((Number) row[1]).intValue();
            System.out.println(quantity);
            usercartList.add(new usercart(itemName, quantity));
        }

        return usercartList;

    }

}


