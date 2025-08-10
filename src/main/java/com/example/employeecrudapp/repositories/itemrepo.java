package com.example.employeecrudapp.repositories;

import com.example.employeecrudapp.entity.item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface itemrepo extends JpaRepository<item,Integer> {
    item findByName(String name);
}
