package br.com.projeto.ecantina.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {
    
    private String email;
    private String password;
    private String type;

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(getEmail(), getPassword());
    }
}
