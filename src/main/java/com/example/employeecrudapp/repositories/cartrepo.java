package com.example.employeecrudapp.repositories;

import com.example.employeecrudapp.entity.cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartrepo extends JpaRepository<cart,Integer> {
}
