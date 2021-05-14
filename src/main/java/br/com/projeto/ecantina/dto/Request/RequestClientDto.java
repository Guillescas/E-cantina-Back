package br.com.projeto.ecantina.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.User;

public class RequestClientDto {
    
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    public String getEmail() {
        return email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public User convertClient() {
        return new Client(getEmail(), getPassword(), getName());
    }
}