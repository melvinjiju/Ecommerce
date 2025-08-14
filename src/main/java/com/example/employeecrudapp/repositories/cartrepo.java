package com.example.employeecrudapp.repositories;

import com.example.employeecrudapp.entity.cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface cartrepo extends JpaRepository<cart,Integer> {

    List<cart> findByEmp_Id(int id);
    List<cart> findByItem_Id(int id);
    List<cart> findByItem_name(String name);



}
