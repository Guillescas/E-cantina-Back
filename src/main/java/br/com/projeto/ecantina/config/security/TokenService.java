package br.com.projeto.ecantina.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.projeto.ecantina.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${ecantina.jwt.expiration}")
    private String expiration;

    @Value("${ecantina.jwt.secret}")
    private String secret;


    public String generateToken(Authentication authentication) {
        User userLogin = (User) authentication.getPrincipal();
        Date today = new Date();
        Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Ecantina")
                .setSubject(userLogin.getId().toString())
                .claim("email", userLogin.getEmail())
                .claim("name", userLogin.getName())
                .claim("type", userLogin.getType())
                .setIssuedAt(today)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public Boolean isTokenValid(String token) {
        try {

            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
            
        } catch (Exception e) {

            return false;
        }
    }


    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }

    public String getTypeUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        return (String) claims.get("type");
    }
}
