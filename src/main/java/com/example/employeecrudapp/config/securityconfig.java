package com.example.employeecrudapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class securityconfig {
    private  jwtauthfilter jwtauthfilter;
        private  AuthenticationProvider authprovider;

    public securityconfig(jwtauthfilter jwtauthfilter, AuthenticationProvider authprovider) {
        this.authprovider = authprovider;
        this.jwtauthfilter = jwtauthfilter;
    }

    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authprovider)  // <-- explicitly add your provider here
                .addFilterBefore(jwtauthfilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
