package com.example.employeecrudapp.repositories;

import com.example.employeecrudapp.entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userrepo extends JpaRepository<employee,Integer> {
    Optional<employee> findByEmail(String email);

}
