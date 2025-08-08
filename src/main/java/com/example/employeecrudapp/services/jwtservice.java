package com.example.employeecrudapp.services;

import com.example.employeecrudapp.entity.Userdetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class jwtservice {
    private final static String secretkey ="7f92fa454cfb9c913832ba3ccdd71baec5cd9c59981d2dced3f27082606a505f";

    public String extractusername(String token){

        return extractclaim(token, Claims::getSubject);
    }
//uncessary for security reasons update security context by setting roles from extracted username like from the database insteead of directly from token
    public List<String> extractRoles(String token) {
        return extractclaim(token, claims -> claims.get("roles", List.class));
    }

    public<T> T extractclaim(String token, Function<Claims,T> claimsresolver){
        final Claims claims = extractallclaims(token);
        return claimsresolver.apply(claims);
    }
    private Claims extractallclaims(String token){
        return Jwts.
                parserBuilder().
                setSigningKey(getsigningkey()).
                build().
                parseClaimsJws(token).getBody();
    }

    private Key getsigningkey() {
        byte[] keybytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keybytes);
    }
    public String generatetoken(
            String role  ,
            Userdetail userdetail
    ){
        return Jwts.builder().claim("role",role).setSubject(userdetail.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 *60*24)).signWith(getsigningkey(), SignatureAlgorithm.HS256)
                .compact();

    }
    public boolean isvalidtoken(String token,Userdetail userdetail){
        return extractusername(token).equals(userdetail.getUsername())&& !istokenexpired(token);
    }

    private boolean istokenexpired(String token) {
        return extractexpiration(token).before(new Date());
    }

    private Date extractexpiration(String token) {
        return extractclaim(token,Claims::getExpiration);
    }

}
