package com.example.employeecrudapp.services;

import com.example.employeecrudapp.entity.Userdetail;
import com.example.employeecrudapp.entity.employee;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userdetailservice implements UserDetailsService {
    private  userrepo service;

    public userdetailservice(userrepo service) {
        this.service = service;
    }

    @Override
    public Userdetail loadUserByUsername(String username) throws UsernameNotFoundException {
        employee employee = service.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new Userdetail(employee);
    }
}
