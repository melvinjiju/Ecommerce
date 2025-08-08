package com.example.employeecrudapp.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.AuthProvider;

@Configuration
public class authprovider {
     private final userdetailservice userdetailservice;

    public authprovider(userdetailservice userdetailservice) {
        this.userdetailservice = userdetailservice;
    }

    @Bean
    public AuthenticationProvider Authprovider(){
        DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider(userdetailservice);
        authprovider.setPasswordEncoder(passwordencorder());
        return authprovider;

    }
    @Bean
    public PasswordEncoder passwordencorder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
