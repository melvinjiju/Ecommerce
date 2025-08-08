package com.example.employeecrudapp.config;

import com.example.employeecrudapp.entity.Userdetail;
import com.example.employeecrudapp.services.jwtservice;
import com.example.employeecrudapp.services.userdetailservice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class jwtauthfilter extends OncePerRequestFilter {
   private jwtservice jwtservice;
   private userdetailservice userdetailservice;

    public jwtauthfilter(jwtservice jwtservice, userdetailservice userdetailservice) {
        this.jwtservice = jwtservice;
        this.userdetailservice = userdetailservice;
    }

    @Override
    protected void doFilterInternal(

             HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String autheader = request.getHeader("Authorization");
        final String jwt;
        if(autheader==null||!autheader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;

        }
        jwt=autheader.substring(7);
       final  String username =jwtservice.extractusername(jwt);
       List<String> role = jwtservice.extractRoles(jwt);
       if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
           Userdetail userdetail = userdetailservice.loadUserByUsername(username);
           if(jwtservice.isvalidtoken(jwt,userdetail)){
               UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userdetail,null,userdetail.getAuthorities());
               authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails((request)));
               SecurityContextHolder.getContext().setAuthentication(authtoken);
           }

       }
    filterChain.doFilter(request,response);


    }
}
